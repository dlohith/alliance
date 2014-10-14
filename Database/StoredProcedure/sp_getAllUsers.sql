

DROP PROCEDURE IF EXISTS sp_getAllUsers;

DELIMITER $$
CREATE PROCEDURE sp_getAllUsers
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
      SELECT userid , firstName ,lastname, loginid,email, phoneno, roleid
        FROM tbl_user
	    WHERE loginid != inloginid
      ORDER BY loginid;
     END IF;
END$$
DELIMITER ;