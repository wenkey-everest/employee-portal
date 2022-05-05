
FROM openjdk:18-jdk-alpine
RUN mvn -B package --file pom.xml -DskipTests
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]



