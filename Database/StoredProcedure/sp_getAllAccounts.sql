DROP PROCEDURE IF EXISTS sp_getAllAccounts;

DELIMITER $$
CREATE PROCEDURE sp_getAllAccounts
(
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	  
	IF (errmsg IS NULL)
     THEN SET errmsg = "";
            
      -- retrieve the item details
      SELECT accountid, userid, balance
        FROM tbl_account
      ORDER BY accountid;
     END IF;
END$$
DELIMITER ;