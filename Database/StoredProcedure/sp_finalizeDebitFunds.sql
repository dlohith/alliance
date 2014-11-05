

DROP PROCEDURE IF EXISTS sp_finalizeDebitFunds;
DELIMITER $$
CREATE PROCEDURE sp_finalizeDebitFunds
(
  IN itransactionid		VARCHAR(100),
  IN iaccountid			VARCHAR(100),
  IN iamount   			LONG,
  IN istatus  			INT,
  IN iloggedinuser	VARCHAR(50),	
  OUT errmsg        VARCHAR(255)    
)
BEGIN


	
    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	
       -- validating the input variables
    IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "Transaction id cannot be empty.";
    END IF;
   
	
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
	 	UPDATE 
             tbl_account SET balance = (balance - iamount) WHERE accountid = iaccountid;
             
         UPDATE 
             tbl_transaction set status = istatus,updatedby= iloggedinuser,updateddate = NOW() where
             transactionid = itransactionid;
         	 
		DELETE from tbl_transactiondebit where transactionid = itransactionid;
             
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
        
END$$
DELIMITER ;