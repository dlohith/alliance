
DROP PROCEDURE IF EXISTS sp_modifyuser;
DELIMITER $$
CREATE PROCEDURE sp_modifyuser
(
	IN inmodifyloginid  VARCHAR(100),
    IN infirstname     VARCHAR(50),
    IN inlastname      VARCHAR(50),
    IN inemail         VARCHAR(50),
    IN inphoneno		VARCHAR(20),
    IN inroleid	    VARCHAR(100),
    IN inupdaterid	    VARCHAR(100),
    OUT errmsg     VARCHAR(255)
    )
BEGIN

	-- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";
	  
	IF (errmsg IS NULL)
     THEN SET errmsg = "";
     
     -- validating the input variables
    IF(inmodifyloginid IS NULL OR inmodifyloginid = "")
	  THEN SET errmsg = "Login id cannot be empty.";
    END IF;
    
    IF(inupdaterid IS NULL OR inupdaterid = "")
	  THEN SET errmsg = "Login id cannot be empty.";
    END IF;
	
	IF(iroleid IS NULL OR iroleid = "")
	  THEN SET errmsg = "Roles cannot be empty.";
    END IF;
            
      -- retrieve the item details
      UPDATE
        tbl_user set firstname = infirstname, lastname = inlastname,
        			 email = inemail, phoneno = inphoneno, roleid = inroleid,
        			 updatedby = inupdaterid , updateddate = NOW()
	    WHERE loginid = inmodifyloginid;
     END IF;
END$$
DELIMITER ;