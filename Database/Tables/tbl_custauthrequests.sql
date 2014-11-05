CREATE TABLE IF NOT EXISTS tbl_custauthrequests
(
  requestid 	VARCHAR(100)    NOT NULL,
  userloginid   VARCHAR(100)	NOT NULL,
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(requestid)
)