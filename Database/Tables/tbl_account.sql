/*******************************************
Name          : tbl_user

Description   : Store details related to Account

Called By     : 

Create By     : Kedar Joshi

Modified Date : 10/14/2014

********************************************/
CREATE TABLE IF NOT EXISTS tbl_account
(
  accountid		VARCHAR(100)	NOT NULL,
  userid        VARCHAR(100)	NOT NULL,
  accounttype	VARCHAR(100)	NOT NULL,
  balance		LONG			NOT NULL,
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(accountid) 
)