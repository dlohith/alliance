

DROP PROCEDURE IF EXISTS sp_changePass;
DELIMITER $$
CREATE PROCEDURE sp_changePass
(
  IN iloginid     	VARCHAR(50),
  IN ipass			VARCHAR(100),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";

    -- validating the input variables
    IF(iloginid IS NULL OR iloginid = "")
	  THEN SET errmsg = "Logged in id cannot be empty.";
    END IF;
    
    -- validating the input variables
    IF(ipass IS NULL OR ipass = "")
	  THEN SET errmsg = "Password cannot be empty.";
    END IF;

	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
		UPDATE 
              tbl_user set password =ipass, initpass = 0, updateddate = now(),updatedby = iloginid
			WHERE loginid = iloginid;	
			
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;