/*******************************************
Name          : tbl_authorizerequest

Description   : Store details related to authorization requests
				by customers to internal bank employees

Called By     : 

Create By     : Kedar Joshi

Modified Date : 10/14/2014

********************************************/
CREATE TABLE IF NOT EXISTS tbl_authorizerequest
(
  requestid 	VARCHAR(100)    NOT NULL,
  userloginid   VARCHAR(100)	NOT NULL,
  status		VARCHAR(100)	NOT NULL,
  bankemplid	VARCHAR(100)	NOT NULL,
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(requestid)
)