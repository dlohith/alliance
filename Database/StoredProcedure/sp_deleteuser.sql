
DROP PROCEDURE IF EXISTS sp_deleteuser;
DELIMITER $$
CREATE PROCEDURE sp_deleteuser
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
      DELETE
        FROM tbl_user
	    WHERE loginid = inloginid;
	    
	  DELETE
        FROM tbl_account
	    WHERE userid = inloginid;
     END IF;
END$$
DELIMITER ;