/*******************************************
Name          : sp_debitFunds

Description   : adds the transaction to
				tbl_debitFunds table

Called By     : 

Create By     : Pavani

Modified Date : 10/29/2014

********************************************/

DROP PROCEDURE IF EXISTS sp_debitFunds;
DELIMITER $$
CREATE PROCEDURE sp_debitFunds
(
  IN itransactionid		VARCHAR(100),
  IN iTransactiontype VARCHAR(10),
  IN iamount   			LONG,  
  IN istatus			INT,
  IN iloggedinuser  VARCHAR(50),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

	DECLARE priority INT ;
	DECLARE iaccountid VARCHAR(100);
	
    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	
       -- validating the input variables
    IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "Transaction id cannot be empty.";
    END IF;
    
	IF(iamount IS NULL OR iamount = "")
	  THEN SET errmsg = "Amount cannot be empty.";
    END IF;
    
    IF(iamount <= 0)
	  THEN SET errmsg = "Amount cannot be zero or negative.";
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
	
	-- SET iaccountid = (SELECT accountid FROM tbl_account where userid = iloggedinuser);
    SELECT accountid INTO iaccountid FROM tbl_account where userid = iloggedinuser;
	
	IF(iaccountid IS NULL OR iaccountid = "")
	  THEN SET errmsg = "Account not found for current user";
    END IF; 
	
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
         INSERT 
             INTO tbl_transaction(transactionid, accountid, transactiontype, priority,
             amount, status, updatedby, updateddate,
             createdby, createddate)
			 VALUES (itransactionid , iaccountid, iTransactiontype,priority,  
			 iamount, istatus, iloggedinuser,NOW(),iloggedinuser,NOW());
         	 
          -- Updating accountid by crediting the amount to the balance
         UPDATE 
             tbl_account SET balance = (balance - iamount) WHERE accountid = iaccountid ;
			  
         -- inserting transaction into credit table with the same transactionid as in transferfunds and transaction
         INSERT 
             INTO tbl_debit(transactionid,accountid, amount, updatedby, updateddate, createdby, createddate)
			 VALUES (itransactionid,iaccountid ,iamount, iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
        
END$$
DELIMITER ;