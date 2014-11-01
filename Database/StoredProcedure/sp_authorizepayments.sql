/*******************************************
Name          : sp_authorizepayments

Description   : adds the transaction to
				tbl_transferFunds table

Called By     : 

Create By     : Sravya

Modified Date : 10/26/2014

********************************************/
DROP PROCEDURE IF EXISTS sp_authorizepayments;
DELIMITER $$
CREATE PROCEDURE sp_authorizepayments
(
  IN itransactionid		VARCHAR(100),
  IN imerchantid		VARCHAR(100),
  IN iuserid			VARCHAR(100),  
  IN itransactiontype	VARCHAR(100),
  IN iamount   			LONG,  
  IN istatus		INT,
  IN irequestid		VARCHAR(100),
  OUT errmsg        VARCHAR(255)    
)
BEGIN
	
	DECLARE priority INT;
	DECLARE iaccountid VARCHAR(100);
	
    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	
       -- validating the input variables
    IF(imerchantid IS NULL OR imerchantid = "")
	  THEN SET errmsg = "Merchant id cannot be empty.";
    END IF; 
    
     IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "Transaction id cannot be empty.";
    END IF;
    
    -- validating the input variables
    IF(iuserid IS NULL OR iuserid = "")
	  THEN SET errmsg = "User id cannot be empty.";
    END IF;      
      
	IF(iamount IS NULL OR iamount = "")
	  THEN SET errmsg = "Amount cannot be empty.";
    END IF;    
      
	IF(iamount >= 5000.00)
		THEN SET priority = 1;
	END IF;
	IF(iamount < 5000.00 && iamount >= 3000.00)
		THEN SET priority = 2;
	END IF;
	IF(iamount < 3000.00)
		THEN SET priority = 3;
	END IF;
	
    SET iaccountid = (SELECT accountid FROM tbl_account WHERE userid = iuserid); 
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
	
	 -- Inserting the record in tbl_transaction table
         INSERT 
             INTO tbl_transaction(transactionid,accountid,transactiontype, priority ,amount, status ,
             updatedby, updateddate, createdby, createddate)
			 VALUES (itransactionid ,iaccountid, itransactiontype , priority ,  
			 iamount, istatus ,imerchantid,NOW(),imerchantid,NOW());	
         
		INSERT 
             INTO tbl_debit(transactionid,accountid, amount , 
             updatedby, updateddate, createdby, createddate)
			 VALUES (itransactionid,iaccountid , iamount ,  
			 imerchantid,NOW(),imerchantid,NOW());		
         
         -- Updating accountid by crediting the amount to the balance
         UPDATE 
             tbl_account SET balance = (balance - iamount) WHERE accountid = iaccountid ;
             
         UPDATE 
         	 tbl_merchantrequests SET status = 2 where requestid = irequestid;
        
         IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
        
         
END$$
DELIMITER ;