
DROP PROCEDURE IF EXISTS sp_deletetransactionotp;
DELIMITER $$
CREATE PROCEDURE sp_deletetransactionotp
(
  IN itransactionid		VARCHAR(100),
  IN iotp     VARCHAR(100),
  OUT errmsg           VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";

    -- validating the input variables
    IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "Transaction id cannot be empty.";
    END IF;
	
	IF(iotp IS NULL OR iotp = "")
	  THEN SET errmsg = "Otp cannot be empty.";
    END IF;
	
    -- Inserting the record into the tbl_user table
	START TRANSACTION;
		DELETE 
             FROM tbl_transferfundotpcheck where transactionid = itransactionid and otp = iotp;
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;