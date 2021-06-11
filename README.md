# SuperChat-Backend-Challenge

A Simple Contact/Message API



## Technology used
1. Java 8 - Spring Boot
2. Maven 3.8.1
3. Docker
4. PostgreSQL

## Running instructions

 From the root folder of the application run:
``` 
docker-compose up
``` 


## Api endpoints

Http GET endpoints:
1. http://localhost:8080/api/contacts
Gets all Contacts


2. http://localhost:8080/api/message
Gets all conversations



Http POST endpoints:
1. http://localhost:8080/api/contacts
With the following JSON in the body:

Creates new contacts.
``` 
{
	"name":"x_name",
	"email":"x_email",
	"telNo":"x_tel_no",
}
``` 
Send message.
e.g.
``` 
{
    "message": "Dear ${contact.name} This is the bitcoin price ${btc.usd}",
    "recevier_ids": [1,2]
}
``` 

You can also use.
``` 
${contact.name}
${contact.email}
${contact.telNo}

${btc.usd}
${btc.eur}
${btc.gbp}
``` 
## Features not implemented
1. Send message with sms etc.(Now just send email).

2. Can be implemented using JWT.
 
3. Authorization/ Authentication


