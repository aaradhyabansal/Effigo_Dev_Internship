
FROM eclipse-temurin:22-jdk-jammy


WORKDIR /app


COPY target/practice-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
