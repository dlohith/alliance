/*******************************************
Name          : sp_getTransferFunds

Description   : adds the transaction to
				tbl_creditFunds table

Called By     : 

Create By     : Pavani

Modified Date : 10/29/2014

********************************************/

DROP PROCEDURE IF EXISTS sp_getTransferFunds;
DELIMITER $$
CREATE PROCEDURE sp_getTransferFunds
(
  IN itransactionid		VARCHAR(100),
  IN iloggedinuser  VARCHAR(50),
  OUT errmsg        VARCHAR(255)    
)
BEGIN
	
    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	
       -- validating the input variables
    IF(itransactionid IS NULL OR itransactionid = "")
	  THEN SET errmsg = "Transaction id cannot be empty.";
    END IF;   
	
   SELECT fromaccountid, toaccountid, amount
	FROM tbl_transferfund
	WHERE transactionid = itransactionid;
        
END$$
DELIMITER ;