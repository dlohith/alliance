DROP PROCEDURE IF EXISTS sp_getAccountBalance;
DELIMITER $$
CREATE PROCEDURE sp_getAccountBalance
(
	IN  iloggedinuser  VARCHAR(100),
    OUT errmsg     VARCHAR(255)
    )
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	  
	IF (errmsg IS NULL)
     THEN SET errmsg = "";
    END IF;
    
	 IF(iloggedinuser IS NULL OR iloggedinuser = "")
	  THEN SET errmsg = "iloggedinuser id cannot be empty.";
     END IF;
     
   
      -- retrieve the item details
      SELECT balance
        FROM tbl_account
	    WHERE userid = iloggedinuser;
    
END$$
DELIMITER ;