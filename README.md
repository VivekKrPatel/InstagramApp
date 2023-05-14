
# Instagram App


## Framework and Language used
- springboot
- java version 19

## Data Structure used in project
- List-ArrayList

## Steps to run project

- Download the source code and import in intellijIDEA.
- Put the properties into application properties of Resource Package
spring.datasource.url=jdbc:mysql://localhost:3306/Instagram
spring.datasource.username=root
spring.datasource.password=MySQL@0101
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true
- Go to localhost:8080/
- For mysql workbench localHost:3306/

## Data flow
1. controller
2. model        
3. Dto
3. service 
4. repository

## Project Summary
     In this project I am creating a Instagram application. So we can do some basic CRUD operations like sign in user,sign up user,update password by email . we can perform operations like save post as well as get post. I have used authentication of user as well as used validations and encrypt passwords in this project.

end.

