

DROP PROCEDURE IF EXISTS sp_getUser;

DELIMITER $$
CREATE PROCEDURE sp_getUser
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
      SELECT userid , firstName ,lastname, loginid,password,email, phoneno, roleid,invalidloginattempts,locked
        FROM tbl_user
	    WHERE loginid = inloginid;
     END IF;
END$$
DELIMITER ;