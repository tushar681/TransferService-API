# TransferService-API

Language, framework and database info :
1) Programming language : Java 11
2) Framework : SpringBoot 2.2.1.RELEASE
3) Database : In Memory H2 database
				a) Using H2 in memory database for storing each action once API is up and running.
				b) Data and schema files are present in /resources folder for DDL and DML operations.
				c) table name : ACCOUNT

Steps to install, update, run and test :
1) run as Maven install
2) Maven update project
3) run as SpringBootApp
4) run as JUnit test

API contract and end-points information:

1) 'GET' request to retrieve information of all accounts from in memory database, end-point "http://localhost:8080/banking/accounts":  
2) `PUT` request to transfer amount from source account to destination account, end-point "http://localhost:8080/banking"
    Sample body (model class - Transaction) : 
    {
	  "sourceAccountNumber": "0934567890786531",
	  "destinationAccountNumber": "0934567890786532",
	  "amount": 300
	}


