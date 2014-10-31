

CREATE TABLE IF NOT EXISTS tbl_transaction
(
  transactionid		VARCHAR(100)	NOT NULL,
  accountid			VARCHAR(100)	NOT NULL,
  transactiontype	VARCHAR(100)	NOT NULL,
  priority			INT				NOT NULL,
  amount		LONG			NOT NULL,
  status		INT				NOT NULL,
  updatedby     VARCHAR(50)		NOT NULL,
  updateddate   TIMESTAMP		NOT NULL,
  createdby     VARCHAR(50)		NOT NULL,
  createddate   DATETIME		NOT NULL,
  PRIMARY KEY(transactionid)
)