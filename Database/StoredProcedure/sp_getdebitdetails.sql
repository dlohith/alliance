DROP PROCEDURE IF EXISTS sp_getdebitdetails;

DELIMITER $$
CREATE PROCEDURE sp_getdebitdetails
(
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
     
      -- retrieve the item details
      SELECT T.transactionid, A.userid, D.amount, "SUCCESS" as Status
        FROM tbl_transaction T INNER JOIN tbl_debit D ON T.transactionid = D.transactionid
        INNER JOIN tbl_account A ON D.accountid = A.accountid;

END$$
DELIMITER ;