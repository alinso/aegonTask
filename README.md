# Aegon Customer Satisfaction Survey


##Technologies Used
I have used Spring Boot, JPA and Mysql as fundamental technologies in this demo. It is restful web service application. 


##Layers

I have standard Entity-Repository-Service-Controller layering in this project. I also have validators and DTOs. 
I always use DTOs because the Object model in the database, and the object model to show the user generally 
differentiates. 

All form data is validated before passing the service layers. I accept only DTO objects
and validate them before usage

I also have exception and util packages.

## Properties
I get properties from Spring Boot's default application.properties file.


## Testing

I have a couple of simple unit tests to show that I can use and write tests. Nothing so practical here.

## Conclusion
It is a basic Spring Boot CRUD sample project I have developed for you. I hope it matches your understanding of 
developing software. Of course it can be much better, but I really have timing crisis these days.