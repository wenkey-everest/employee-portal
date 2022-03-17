FROM openjdk:18-jdk-alpine
COPY target/*.jar yash.jar
ENTRYPOINT ["java", "-jar", "/yash.jar"]

