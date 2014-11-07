DROP PROCEDURE IF EXISTS sp_getuseridformerchantrequest;

DELIMITER $$
CREATE PROCEDURE sp_getuseridformerchantrequest
(
	IN irequestid	VARCHAR(100),
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
     
      -- retrieve the item details
      SELECT userloginid from tbl_merchantrequests where requestid = irequestid;

END$$
DELIMITER ;