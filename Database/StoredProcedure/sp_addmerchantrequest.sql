/*******************************************
Name          : sp_addmerchantrequest

Description   : adds the account to
				tbl_merchantrequest table

Called By     : 

Create By     : Kedar

Modified Date : 11/1/2014

********************************************/
DROP PROCEDURE IF EXISTS sp_addmerchantrequest;
DELIMITER $$
CREATE PROCEDURE sp_addmerchantrequest
(
  IN irequestid 		VARCHAR(100),
  IN imerchantloginid	VARCHAR(100),
  IN iuserloginid     	VARCHAR(50),
  IN iamount		    LONG,
  IN istatus			INT,
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";

    -- validating the input variables
    IF(irequestid IS NULL OR irequestid = "")
	  THEN SET errmsg = "Request id cannot be empty.";
    END IF;
    
    IF(imerchantloginid IS NULL OR imerchantloginid = "")
	  THEN SET errmsg = "MerchantLogin id cannot be empty.";
    END IF;
    
	IF(iuserloginid IS NULL OR iuserloginid = "")
	  THEN SET errmsg = "UserLogin id cannot be empty.";
    END IF;

    -- Inserting the record into the tbl_account table
	START TRANSACTION;
		INSERT 
             INTO tbl_merchantrequests(requestid, merchantloginid, userloginid, amount, status, updatedby,
             updateddate, createdby, createddate)
			 VALUES (irequestid, imerchantloginid,iuserloginid,  
			 iamount, istatus, merchantloginid,NOW(),merchantloginid,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;