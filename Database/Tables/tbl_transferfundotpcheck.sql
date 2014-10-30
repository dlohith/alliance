
CREATE TABLE IF NOT EXISTS tbl_transferfundotpcheck
(
  transactionid		VARCHAR(100)	NOT NULL,
  otp        VARCHAR(100)	NOT NULL,
  updatedby     VARCHAR(50)   NOT NULL,
  updateddate   TIMESTAMP     NOT NULL,
  createdby     VARCHAR(50)   NOT NULL,
  createddate   DATETIME      NOT NULL
)