# Backend-side to Lingua Start mobile application

## Development environment
**JDK**: 1.8  
**Language**: Kotlin 1.2  
**Database**: MySQL 5.7  
**Security**: OAuth 2  
**Deployment**: Docker Compose  
**Dependecies**:  
 - Springboot 2
 - Spring security 5
 
## Deployment by Docker Compose ##
**0. Install Docker Compose**  
**1. Check installation of JDK 1.8, [install JDK 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) if not installed** 
```
java -version
```

 
## Manual deployment

**1. [Download](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) and install JDK 1.8.**  
**2. [Download](https://dev.mysql.com/downloads/mysql/) and install MySQL 5.7.**  
**3. Create database:**  
  * Login in mysql console:
```
sudo mysql -u root -p
```
  * Create db user:
```
GRANT ALL PRIVILEGES ON *.* TO 'lingua_start_user'@'localhost' IDENTIFIED BY 'password_gBkYntkZ7P8Zs59a';
```
  * Logout from root
```
\q
```
  * Login as user:
```
sudo mysql -u lingua_start_user -p
```
  * Create database:
```
CREATE DATABASE lingua_start_db;
```
  * Import database dump with scheme:
```
TODO: Describe this step
```
**4. Setup enviroment variables:**  
```
DATABASE_NAME=lingua_start_db;
DATABASE_USER=lingua_start_user;
DATABASE_PASSWORD=password_gBkYntkZ7P8Zs59a;
DATABASE_HOST=localhost;
DATABASE_PORT=3306
```
**5. Build app**  
```
./gradlew build 
```
**6. Run app**
```
java -jar build/libs/start-0.0.1-SNAPSHOT.jar
```
