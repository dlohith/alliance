
DROP PROCEDURE IF EXISTS sp_isLoginUnique;
DELIMITER $$
CREATE PROCEDURE sp_isLoginUnique
(
	IN inloginid  VARCHAR(100),
    OUT errmsg     VARCHAR(255)
    )
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	  
	IF (errmsg IS NULL)
     THEN SET errmsg = "";
            
      -- retrieve the item details
      SELECT 1
        FROM tbl_user
	    WHERE loginid = inloginid;
     END IF;
END$$
DELIMITER ;