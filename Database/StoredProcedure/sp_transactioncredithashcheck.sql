DROP PROCEDURE IF EXISTS sp_transactioncredithashcheck;
DELIMITER $$
CREATE PROCEDURE sp_transactioncredithashcheck
(
  IN itransactionid		VARCHAR(100),
  IN ihash     VARCHAR(100),
  OUT errmsg           VARCHAR(255)    
)
BEGIN
	select 1 as isexpired from tbl_transactioncredit where transactionid = itransactionid and hashedvalue = ihash; 
		
		 
END$$
DELIMITER ;