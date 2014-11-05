DROP PROCEDURE IF EXISTS sp_getpendingmerchantrequests;
DELIMITER $$
CREATE PROCEDURE sp_getpendingmerchantrequests
(
  IN  iloginid		VARCHAR(255),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
 
	SELECT requestid, merchantloginid, userloginid, amount, status
	FROM tbl_merchantrequests
	WHERE status = 1  -- 1 is for pending
	AND userloginid = iloginid;
END$$
DELIMITER ;