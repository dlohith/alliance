DROP PROCEDURE IF EXISTS sp_transactiondebithashcheck;
DELIMITER $$
CREATE PROCEDURE sp_transactiondebithashcheck
(
  IN itransactionid		VARCHAR(100),
  IN ihash     VARCHAR(100),
  OUT errmsg           VARCHAR(255)    
)
BEGIN
	select 1 as isexpired from tbl_transactiondebit where transactionid = itransactionid and hashedvalue = ihash; 
		
		 
END$$
DELIMITER ;