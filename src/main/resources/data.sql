DROP TABLE IF EXISTS ACCOUNT;
 
CREATE TABLE ACCOUNT (
  account_number VARCHAR(50) PRIMARY KEY,
  balance float NOT NULL
);

INSERT INTO 
	ACCOUNT (account_number, balance) 
VALUES
  	('0934567890786531',400.50),
  	('0934567890786532',100),
	('0934567890786533',50),
	('0934567890786534',70);