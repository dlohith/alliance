

DROP PROCEDURE IF EXISTS sp_hasAccount;
DELIMITER $$
CREATE PROCEDURE sp_hasAccount
(
  IN iuserid     	VARCHAR(50),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";

    
	IF(iuserid IS NULL OR iuserid = "")
	  THEN SET errmsg = "User id cannot be empty.";
    END IF;

    IF EXISTS(SELECT 1 FROM tbl_account
                WHERE accountid = iaccountid)
      THEN SET errmsg = "Account id already exists.";
	END IF;
	
    -- Inserting the record into the tbl_account table
	SELECT 1 FROM tbl_account
                WHERE userid = iuserid;
END$$
DELIMITER ;