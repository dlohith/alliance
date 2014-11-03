

DROP PROCEDURE IF EXISTS sp_updateFailedAttempts;
DELIMITER $$
CREATE PROCEDURE sp_updateFailedAttempts
(
  IN iloginid     	VARCHAR(50),
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

	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
		UPDATE 
              tbl_user set invalidloginattempts = (invalidloginattempts + 1),updateddate = now(),updatedby = iloginid
			WHERE loginid = iloginid;
		UPDATE 
              tbl_user set locked = 1,updateddate = now(),updatedby = iloginid
			WHERE loginid = iloginid and invalidloginattempts >= 3;	
			
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;