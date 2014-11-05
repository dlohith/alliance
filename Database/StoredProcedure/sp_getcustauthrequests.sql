DROP PROCEDURE IF EXISTS sp_getcustauthrequests;

DELIMITER $$
CREATE PROCEDURE sp_getcustauthrequests
(
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
             
      -- retrieve the item details
      SELECT requestid, userloginid
        FROM tbl_custauthrequests;
END$$
DELIMITER ;