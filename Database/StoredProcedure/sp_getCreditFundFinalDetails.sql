DROP PROCEDURE IF EXISTS sp_getCreditFundFinalDetails;

DELIMITER $$
CREATE PROCEDURE sp_getCreditFundFinalDetails
(
	IN itransactionid  VARCHAR(100),
    OUT errmsg     VARCHAR(255)
)
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
      
	IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "itransactionid id cannot be empty.";
    END IF;
    
      -- retrieve the item details
      SELECT accountid, amount FROM tbl_credit WHERE transactionid  = itransactionid;
     
END$$
DELIMITER ;