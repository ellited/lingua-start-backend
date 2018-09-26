FROM java:8
LABEL maintainer="rudenko.michael@gmail.com"
WORKDIR /app
COPY  build/libs/start-0.0.1-SNAPSHOT.jar /app/spring-boot-app.jar
ENTRYPOINT ["java","-jar","spring-boot-app.jar"]