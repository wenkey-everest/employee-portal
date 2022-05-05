FROM openjdk:18-jdk-alpine
FROM maven:3.8.1-jdk-11
RUN mvn clean package -Dmaven.test.skip=true
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]



