DROP PROCEDURE IF EXISTS sp_otpTransferFunds;
DELIMITER $$
CREATE PROCEDURE sp_otpTransferFunds
(
  IN itransactionid		VARCHAR(100),
  IN ifromaccountid VARCHAR(100),
  IN itoaccountid	VARCHAR(100),
  IN iamount		LONG,
  IN iotp 			VARCHAR(100),
  IN istatus		INT,
  IN iloggedinuser  VARCHAR(50),
  OUT errmsg        VARCHAR(255)    
)
BEGIN
		
    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	
       -- validating the input variables
    IF(iotp IS NULL OR iotp = "")
	  THEN SET errmsg = "Otp id cannot be empty.";
    END IF;     
    
     IF(istatus IS NULL OR istatus = "")
	  THEN SET errmsg = "Status cannot be empty.";
    END IF; 
    
    IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "Transaction id cannot be empty.";
    END IF; 
    
     IF(ifromaccountid IS NULL OR ifromaccountid = "")
	  THEN SET errmsg = "From account id cannot be empty.";
    END IF; 
    
     IF(itoaccountid IS NULL OR itoaccountid = "")
	  THEN SET errmsg = "To Account id cannot be empty.";
    END IF; 
    
     IF(iamount IS NULL OR iamount = "")
	  THEN SET errmsg = "Amount cannot be empty.";
    END IF; 	
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;			               
                
         UPDATE 
             tbl_account SET balance = (balance - iamount) WHERE userid = ifromaccountid ;
             
          UPDATE 
             tbl_account SET balance = (balance + iamount) WHERE userid = itoaccountid ;    
             
         UPDATE 	
			tbl_transaction SET status = istatus WHERE transactionid = itransactionid; 
			
		DELETE 
             FROM tbl_transferfundotpcheck WHERE transactionid = itransactionid AND otp = iotp;
				
                
         IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
        
         
END$$
DELIMITER ;