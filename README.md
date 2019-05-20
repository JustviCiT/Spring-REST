# Spring-REST

Example of a restful service with spring

Structure of the project:

```
src/main/java
	com.rest
		Application
		ClientController
		ClientRepository
		ClientResourceAssembler
		OrderController
		OrderRepository
		OrderResourceAssembler
	com.rest.entity
		Client
		Order
	com.rest.error
		ClientNotFoundAdvice
		ClientNotFoundException
		OrderNotFoundAdvice
		OrderNotFoundException
	
src/main/resources
	application.properties		
```
Before starting check the application properties connection parameters.


Create a mysql database, user and update the application properties accordingly:

```
mysql> create database rest_example; -- Create the new database
mysql> create user 'webrest'@'%' identified by 'webrest'; -- Creates the user
mysql> grant all on rest_example.* to 'webrest'@'%'; -- Gives all the privileges to the new user on the newly created database
```
To compile:

```
mvn install
cd target/
java -jar rest-example-0.0.1-SNAPSHOT.jar
```

Endpoints available: 

```
GET http://localhost:8080/clients/{id_client}
POST http://localhost:8080/clients/
PUT http://localhost:8080/clients/{id_client}
DELETE http://localhost:8080/clients/{id_client}
GET http://localhost:8080/clients/search/{ragione_sociale}

GET http://localhost:8080/orders/{id_ordine}
POST http://localhost:8080/clients/{id_client}/orders
PUT http://localhost:8080/orders/{id_ordine}
DELETE http://localhost:8080/orders/{id_ordine}
```
