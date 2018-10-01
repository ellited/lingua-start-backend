# Backend-side to Lingua Start mobile application

Development environment:  
**JDK**: 1.8  
**Language**: Kotlin 1.2  
**Database**: MySQL 5.7  
**Security**: OAuth 2  
**Deployment**: Docker Compose  
**Dependecies**:  
 - Springboot 2
 - Spring security 5 

Install JDK 1.8:
https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

Install MySQL:
https://dev.mysql.com/downloads/mysql/

Create database 
Login in mysql console:
	sudo mysql -u root -p
Create db user:
	GRANT ALL PRIVILEGES ON *.* TO 'lingua_start_user'@'localhost' IDENTIFIED BY 'password_gBkYntkZ7P8Zs59a';
Logout from root
	\q
Login as user:
	sudo mysql -u lingua_start_user -p
Create database:
	CREATE DATABASE lingua_start_db;

Setup enviroment variables:
	DATABASE_NAME=lingua_start_db;DATABASE_USER=lingua_start_user;DATABASE_PASSWORD=password_gBkYntkZ7P8Zs59a;DATABASE_HOST=localhost;DATABASE_PORT=3306

Build app
	 ./gradlew build 
