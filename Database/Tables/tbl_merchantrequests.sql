/*******************************************
Name          : tbl_merchantrequests

Description   : Store details related to merchant requests

Called By     : 

Create By     : Kedar Joshi

Modified Date : 11/1/2014

********************************************/
CREATE TABLE IF NOT EXISTS tbl_merchantrequests
(
  requestid 		VARCHAR(100)    NOT NULL,
  merchantloginid	VARCHAR(100)	NOT NULL,
  userloginid       VARCHAR(100)	NOT NULL,
  amount			LONG			NOT NULL,
  status			INT,
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(requestid)
);