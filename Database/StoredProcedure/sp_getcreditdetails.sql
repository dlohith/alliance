DROP PROCEDURE IF EXISTS sp_getcreditdetails;

DELIMITER $$
CREATE PROCEDURE sp_getcreditdetails
(
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
     
      -- retrieve the item details
      SELECT T.transactionid, A.userid, C.amount, "SUCCESS" as Status
        FROM tbl_transaction T INNER JOIN tbl_credit C ON T.transactionid = C.transactionid
        INNER JOIN tbl_account A ON C.accountid = A.accountid;

END$$
DELIMITER ;