DROP PROCEDURE IF EXISTS sp_getmerchantrequest;
DELIMITER $$
CREATE PROCEDURE sp_getmerchantrequest
(
  IN irequestid 	VARCHAR(100),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";

    -- validating the input variables
    IF(irequestid IS NULL OR irequestid = "")
	  THEN SET errmsg = "Request id cannot be empty.";
    END IF;
  
	SELECT merchantloginid, userloginid, amount, status
	FROM tbl_merchantrequests
	WHERE requestid = irequestid;
END$$
DELIMITER ;