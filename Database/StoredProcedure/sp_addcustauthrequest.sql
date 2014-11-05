DROP PROCEDURE IF EXISTS sp_addcustauthrequest;
DELIMITER $$
CREATE PROCEDURE sp_addcustauthrequest
(
  IN irequestid 	VARCHAR(100),
  IN iuserloginid 	VARCHAR(100),
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

    IF EXISTS(SELECT 1 FROM tbl_custauthrequests
                WHERE iuserloginid = userloginid)
      THEN SET errmsg = "Request already exists.";
	END IF;

	START TRANSACTION;
		INSERT 
             INTO tbl_custauthrequests(requestid, userloginid, updatedby, updateddate, createdby, createddate)
			 VALUES (irequestid,iuserloginid, iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;