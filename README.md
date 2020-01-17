# Digital Hub - Backend Assignment - CP

## Followed approach 

### Stack

This application was developed using Spring boot, Maven and Kotlin as main technologies. <br/>

### Assumptions
Code was written with the assumption that there is another service in charge to made CRUD operations over Account entities. 
We initialize the H2 Database with the following accounts to mock a real scenario:
```bash
{ 
    "owner" = 123456789,
    "account" = 123456789,
    "balance" = 0.0,
    "createdAt" = date
}
{ 
    "owner"= 123456788,
    "account" = 123456788,
    "balance" = 34567.0,
    "createdAt" = date
}
{
    "owner" = 123456787,
    "account" = 123456787,
    "balance" = 56784.0,
    "createdAt" = date
}
{
    "owner" = 123456786,
    "account" = 123456786,
    "balance" = 3452.0,
    "createdAt" = date
}
{
    "owner" = 123456786,
    "account" = 123456786,
    "balance" = -500.0,
    "createdAt" = date
}
```
<br/>

### Code highlights
Based on the previous statement, I built ***TransactionService*** 
to provide the required functionality to transfer money between accounts.<br/>
The main functions inside ***TransactionService*** are _Validator_, _ModifyBalance_ and _SendMoney_ functions:
<br/>

1. Validator: this function verifies the following conditions: 
<br/>a) Accounts exist 
<br/>b) Accounts' balance is not under $-500 before and after executing transactions 
<br/>
<br/>
2. ModifyBalance: updates balance attribute for a given account adding or reducing the amount 
<br/>
<br/>
3. SendMoney: first call *Validator* to check if the transactions is valid.
If the validation fails Error ResponseEntity is thrown, otherwise *ModifyBalance* is called to 
add and reduce the corresponding amount from each account. 
<br/>
<br/>
To give more detail about a transaction rejection, a header named "error" is added to Error ResponseEntity with the reason.

## How to run Application:
In order to run the application, you may execute the command ***"java -jar transactions-0.0.1-SNAPSHOT.jar"*** <br/>
Then access to localhost:8080/api and you can interact with documented methods provided by *springdoc-openapi-ui* library

## Repository
[josigtz/Digital-Hub-Backend-Assignment](https://github.com/josigtz/Digital-Hub-Backend-Assignment)
