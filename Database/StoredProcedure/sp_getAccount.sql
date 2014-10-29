DROP PROCEDURE IF EXISTS sp_getAccount;

DELIMITER $$
CREATE PROCEDURE sp_getAccount
(
	IN iaccountid  VARCHAR(100),
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
      
	IF(iaccountid IS NULL OR iaccountid = "")
	  THEN SET errmsg = "Account id cannot be empty.";
    END IF;
	  
	IF (errmsg IS NULL)
     THEN SET errmsg = "";
            
      -- retrieve the item details
      SELECT accountid, userid, balance
        FROM tbl_account
	    WHERE accountid = iaccountid;
     END IF;
END$$
DELIMITER ;