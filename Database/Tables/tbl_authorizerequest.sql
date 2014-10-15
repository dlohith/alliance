/*******************************************
Name          : tbl_user

Description   : Store details related to authorization requests

Called By     : 

Create By     : Kedar Joshi

Modified Date : 10/14/2014

********************************************/
CREATE TABLE IF NOT EXISTS tbl_authorizerequest
(
  accountid		VARCHAR(100)	NOT NULL,
  userid        VARCHAR(100)	NOT NULL,
  isapproved	BOOLEAN			NOT NULL,
  bankemplid	VARCHAR(100)	NOT NULL,
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(accountid, userid),
  FOREIGN KEY(accountid) REFERENCES tbl_account(accountid) ON UPDATE CASCADE,
  FOREIGN KEY(userid) REFERENCES tbl_user(userid) ON UPDATE CASCADE
)