DROP PROCEDURE IF EXISTS sp_getauthorizerrequestsforemployee;
DELIMITER $$
CREATE PROCEDURE sp_getauthorizerrequestsforemployee
(
  IN  ibankemplid   VARCHAR(255),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

SELECT M.requestid, M.merchantloginid, M.userloginid, M.amount, M.status
FROM tbl_merchantrequests M INNER JOIN tbl_authorizerequest A on M.userloginid = A.userloginid
WHERE A.bankemplid = ibankemplid;

END$$
DELIMITER 