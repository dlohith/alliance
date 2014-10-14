/*******************************************
Name          : sp_addUser

Description   : adds the user request to
				tbl_user table

Called By     : 

Create By     : Lohith

Modified Date : 10/13/2014

********************************************/

DROP PROCEDURE IF EXISTS sp_addUser;
DELIMITER $$
CREATE PROCEDURE sp_addUser
(
  IN iuserid		VARCHAR(100),
  IN ifirstname     VARCHAR(50),
  IN ilastname      VARCHAR(50),
  IN iloginid      VARCHAR(50),
  IN ipassword      VARCHAR(200),
  IN iemail         VARCHAR(50),
  IN iphoneno		VARCHAR(20),
  IN iroleid	    VARCHAR(100),
  IN iloggedinuser    VARCHAR(50),
  OUT errmsg           VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";

    -- validating the input variables
    IF(iloginid IS NULL OR iloginid = "")
	  THEN SET errmsg = "Login id cannot be empty.";
    END IF;

    IF EXISTS(SELECT 1 FROM tbl_user
                WHERE loginid = iloginid)
      THEN SET errmsg = "Login id already exists.";
	END IF;
	
	-- validating the input variables
    IF(iuserid IS NULL OR iuserid = "")
	  THEN SET errmsg = "User id cannot be empty.";
    END IF;

    IF EXISTS(SELECT 1 FROM tbl_user
                WHERE userid = iuserid)
      THEN SET errmsg = "User id already exists.";
	END IF;
	
	IF(ipassword IS NULL OR ipassword = "")
	  THEN SET errmsg = "Password cannot be empty.";
    END IF;
	
	IF(iroleid IS NULL OR iroleid = "")
	  THEN SET errmsg = "Roles cannot be empty.";
    END IF;

    -- Inserting the record into the tbl_user table
	START TRANSACTION;
		INSERT 
             INTO tbl_user(userid ,  firstname ,  lastname,  
			 loginid, password, email, phoneno, roleid, updatedby, updateddate, createdby, createddate)
			 VALUES (iuserid ,  ifirstname ,  ilastname,  
			 iloginid, ipassword, iemail, iphoneno, iroleid,
                     iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;