

DROP PROCEDURE IF EXISTS sp_transactiondebithash;
DELIMITER $$
CREATE PROCEDURE sp_transactiondebithash
(
  IN itransactionid		VARCHAR(100),
  IN ihashedvalue VARCHAR(100),
  IN iloggedinuser  VARCHAR(50),
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
    
	IF(ihashedvalue IS NULL OR ihashedvalue = "")
	  THEN SET errmsg = "hashed value cannot be empty.";
    END IF;
    
    IF(iloggedinuser IS NULL OR iloggedinuser = "")
	  THEN SET errmsg = "iloggedinuser cannot be empty.";
    END IF;
    
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
         INSERT 
             INTO tbl_transactiondebit(transactionid, hashedvalue, updatedby, updateddate,
             createdby, createddate)
			 VALUES (itransactionid , ihashedvalue, iloggedinuser,NOW(),iloggedinuser,NOW());
         	 
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
        
END$$
DELIMITER ;