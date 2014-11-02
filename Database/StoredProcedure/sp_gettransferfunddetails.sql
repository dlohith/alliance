DROP PROCEDURE IF EXISTS sp_getrransferdunddetails;

DELIMITER $$
CREATE PROCEDURE sp_getrransferdunddetails
(
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
     
      -- retrieve the item details
       SELECT T.transactionid, TF.fromaccountid, TF.toaccountid, TF.amount, T.status
        FROM tbl_transaction T INNER JOIN tbl_transferfund TF ON T.transactionid = TF.transactionid

END$$
DELIMITER ;