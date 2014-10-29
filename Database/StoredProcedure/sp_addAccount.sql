/*******************************************
Name          : sp_addAccount

Description   : adds the account to
				tbl_account table

Called By     : 

Create By     : Kedar

Modified Date : 10/16/2014

********************************************/

DROP PROCEDURE IF EXISTS sp_addAccount;
DELIMITER $$
CREATE PROCEDURE sp_addAccount
(
  IN iaccountid		VARCHAR(100),
  IN iuserid     	VARCHAR(50),
  IN ibalance		LONG,
  IN iloggedinuser  VARCHAR(50),
  OUT errmsg        VARCHAR(255)    
)
BEGIN

    -- the error handler for any sql exception
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
      SET errmsg = "SQL exception has occurred";

    -- validating the input variables
    IF(iaccountid IS NULL OR iaccountid = "")
	  THEN SET errmsg = "Account id cannot be empty.";
    END IF;
    
	IF(iuserid IS NULL OR iuserid = "")
	  THEN SET errmsg = "User id cannot be empty.";
    END IF;

    IF EXISTS(SELECT 1 FROM tbl_account
                WHERE accountid = iaccountid)
      THEN SET errmsg = "Account id already exists.";
	END IF;
	
    -- Inserting the record into the tbl_account table
	START TRANSACTION;
		INSERT 
             INTO tbl_account(accountid, userid, balance, updatedby, updateddate, createdby, createddate)
			 VALUES (iaccountid ,  iuserid ,  
			 ibalance, iloggedinuser,NOW(),iloggedinuser,NOW());
		 IF (errmsg IS NULL)
           THEN COMMIT;
         ELSE ROLLBACK;
         END IF;
END$$
DELIMITER ;