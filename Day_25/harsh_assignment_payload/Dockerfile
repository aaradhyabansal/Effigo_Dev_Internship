
FROM eclipse-temurin:22-jdk-jammy


WORKDIR /app


COPY target/practice-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8081


ENTRYPOINT ["java", "-jar", "app.jar"]
