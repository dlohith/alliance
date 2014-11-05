DROP PROCEDURE IF EXISTS sp_getallmerchantrequestsmer;
DELIMITER $$
CREATE PROCEDURE sp_getallmerchantrequestsmer
(
  IN imerchantloginid VARCHAR(255),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
 
	SELECT requestid, merchantloginid, userloginid, amount, status
	FROM tbl_merchantrequests
	WHERE merchantloginid = imerchantloginid;
END$$
DELIMITER ;