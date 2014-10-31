
CREATE TABLE IF NOT EXISTS tbl_transferfund
(
  transactionid		VARCHAR(100)	NOT NULL, 
  fromaccountid		VARCHAR(100)	NOT NULL,
  toaccountid		VARCHAR(100)	NOT NULL,
  amount		    LONG			NOT NULL,   
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  FOREIGN KEY(transactionid) REFERENCES tbl_transaction(transactionid) ON UPDATE CASCADE
)