/*******************************************
Name          : tbl_user

Description   : Store the details of users.

Called By     : 

Create By     : Dwaraka Lohith

Modified Date : 10/13/2014

********************************************/
CREATE TABLE IF NOT EXISTS tbl_user
(
  userid        VARCHAR(100)   NOT NULL,
  firstname     VARCHAR(50)   DEFAULT NULL,
  lastname      VARCHAR(50)   DEFAULT NULL,
  loginid      VARCHAR(50)    NOT NULL UNIQUE,
  password      VARCHAR(200)   NOT NULL,
  email         VARCHAR(50)   DEFAULT NULL,
  phoneno		VARCHAR(20)   DEFAULT NULL,
  roleid	    VARCHAR(100)  NOT NULL,
  updatedby     VARCHAR(50)   NOT NULL,
  updateddate   TIMESTAMP     NOT NULL,
  createdby     VARCHAR(50)   NOT NULL,
  createddate   DATETIME      NOT NULL,
  PRIMARY KEY(userid) 
)