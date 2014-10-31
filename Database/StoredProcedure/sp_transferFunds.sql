/*******************************************
Name          : sp_transferFunds

Description   : adds the transaction to
				tbl_transferFunds table

Called By     : 

Create By     : Sravya

Modified Date : 10/26/2014

********************************************/

DROP PROCEDURE IF EXISTS sp_transferFunds;
DELIMITER $$
CREATE PROCEDURE sp_transferFunds
(
  IN itransactionid		VARCHAR(100),
  IN ifromaccountid		VARCHAR(100),
  IN itoaccountid     	VARCHAR(100),  
  IN iamount   			LONG,  
  IN istatus		INT,
  IN iloggedinuser  VARCHAR(50),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

	DECLARE priority INT ;		
	
    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	
       -- validating the input variables
    IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "Transaction id cannot be empty.";
    END IF;      
    
    -- validating the input variables
    IF(ifromaccountid IS NULL OR ifromaccountid = "")
	  THEN SET errmsg = "From Account id cannot be empty.";
    END IF;
    
    IF(ifromaccountid != (SELECT accountid FROM tbl_account
    						WHERE userid = iloggedinuser))
    	THEN SET errmsg = "The account id does not belong to the user id";
    END IF;
    
    IF(itoaccountid IS NULL OR itoaccountid = "")
	  THEN SET errmsg = "To Account id cannot be empty.";
    END IF;    
    
    IF(itoaccountid != (SELECT accountid FROM tbl_account
    						WHERE accountid = itoaccountid))
    	THEN SET errmsg = "The account id does not belong to this bank";
    END IF;
    
	IF(iamount IS NULL OR iamount = "")
	  THEN SET errmsg = "Amount cannot be empty.";
    END IF;
    
    IF(iamount >= (SELECT balance FROM tbl_account
                WHERE accountid = ifromaccountid))
	  THEN SET errmsg = "Amount cannot be greater than the balance";
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
	
	
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;	
		INSERT 
             INTO tbl_transferfund(transactionid,fromaccountid, toaccountid, amount, updatedby, updateddate, createdby, createddate)
			 VALUES (itransactionid,ifromaccountid , itoaccountid ,  
			 iamount, iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
         
         -- Inserting the record in tbl_transaction table
         INSERT 
             INTO tbl_transaction(transactionid,accountid,transactiontype, priority ,amount, updatedby, updateddate, createdby, createddate)
			 VALUES (itransactionid ,ifromaccountid,  'TF' ,  priority,  
			 iamount, iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
         
         -- Updating fromaccountid by debiting the amount from the balance
         UPDATE 
             tbl_account SET balance = (balance - iamount) WHERE accountid = ifromaccountid ;
			 
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
         
          -- Updating toaccountid by crediting the amount to the balance
         UPDATE 
             tbl_account SET balance = (balance + iamount) WHERE accountid = itoaccountid ;
			 
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
         
         -- inserting transaction into credit table with the same transactionid as in transferfunds and transaction
         INSERT 
             INTO tbl_credit(transactionid,accountid, amount, updatedby, updateddate, createdby, createddate)
			 VALUES (itransactionid,itoaccountid ,iamount, iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
         
         -- inserting transaction into debit table with the same transactionid as in transferfunds and transaction
          INSERT 
             INTO tbl_debit(transactionid,accountid, amount, updatedby, updateddate, createdby, createddate)
			 VALUES (itransactionid,ifromaccountid ,iamount, iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
         
END$$
DELIMITER ;