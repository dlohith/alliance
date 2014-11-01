DROP PROCEDURE IF EXISTS sp_rejectmerchantrequest;
DELIMITER $$
CREATE PROCEDURE sp_rejectmerchantrequest
(
  IN irequestid		VARCHAR(100),
  OUT errmsg        VARCHAR(255)    
)
BEGIN
	
	 DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
	  SET errmsg = "SQL exception has occurred";
	  
    IF(irequestid IS NULL OR irequestid = "")
	  THEN SET errmsg = "Request id cannot be empty.";
    END IF; 
 
	START TRANSACTION;
         -- Updating accountid by crediting the amount to the balance
         UPDATE 
             tbl_merchantrequests SET status = 3 WHERE requestid = irequestid ;
 
         IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
        
         
END$$
DELIMITER ;