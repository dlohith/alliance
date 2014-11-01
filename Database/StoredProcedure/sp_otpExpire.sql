DROP PROCEDURE IF EXISTS sp_otpexpire;
DELIMITER $$
CREATE PROCEDURE sp_otpexpire
(
  IN itransactionid		VARCHAR(100),
  IN iotp     VARCHAR(100),
  OUT errmsg           VARCHAR(255)    
)
BEGIN
    -- OTP Expire
	select TIMESTAMPDIFF(SECOND,createddate,NOW())<900 as isexpired from tbl_transferfundotpcheck where transactionid = itransactionid and otp = iotp; 
		
		 
END$$
DELIMITER ;