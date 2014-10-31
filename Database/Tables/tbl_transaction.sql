/*******************************************
Name          : tbl_user

Description   : Store details related to Account

Called By     : 

Create By     : Sai Sravya Guturu

Modified Date : 10/14/2014

********************************************/

CREATE TABLE IF NOT EXISTS tbl_transaction
(
  transactionid		VARCHAR(100)	NOT NULL,
  accountid			VARCHAR(100)	NOT NULL,
  transactiontype	VARCHAR(100)	NOT NULL,
  priority			INT				NOT NULL,
  amount		LONG			NOT NULL,
  status		VARCHAR(100)	NOT NULL,
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(transactionid),
  FOREIGN KEY(transactionid) REFERENCES tbl_transferfund(transactionid) ON UPDATE CASCADE
)