
CREATE TABLE IF NOT EXISTS tbl_transactioncredit
(
  transactionid VARCHAR(100)	NOT NULL,
  hashedvalue	VARCHAR(100)	NOT NULL,  
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(transactionid) 
)