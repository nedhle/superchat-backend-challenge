FROM openjdk:8-jdk-alpine
MAINTAINER alican

ADD target/superchat-backend-challenge-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]