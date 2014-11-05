

DROP PROCEDURE IF EXISTS sp_isFirstLogin;
DELIMITER $$
CREATE PROCEDURE sp_isFirstLogin
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
		select 1 from
              tbl_user 
			WHERE loginid = iloginid and initpass = 1;	

END$$
DELIMITER ;