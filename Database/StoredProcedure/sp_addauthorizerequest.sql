DROP PROCEDURE IF EXISTS sp_addauthorizerequest;
DELIMITER $$
CREATE PROCEDURE sp_addauthorizerequest
(
  IN irequestid 	VARCHAR(100),
  IN iuserloginid 	VARCHAR(100),
  IN istatus		VARCHAR(100),
  IN ibankemplid	VARCHAR(100),
  IN iloggedinuser  VARCHAR(100),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
    
	IF(iuserloginid IS NULL OR iuserloginid = "")
	  THEN SET errmsg = "User id cannot be empty.";
    END IF;

    IF EXISTS(SELECT 1 FROM tbl_authorizerequest
                WHERE iuserloginid = userloginid)
      THEN SET errmsg = "Request already exists.";
	END IF;
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
		INSERT 
             INTO tbl_authorizerequest(requestid,userloginid,status,bankemplid,updatedby, updateddate, createdby, createddate)
			 VALUES (irequestid,iuserloginid, istatus,ibankemplid, iloggedinuser,NOW(),iloggedinuser,NOW());
			 
		 DELETE FROM tbl_custauthrequests WHERE userloginid = iuserloginid;
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;