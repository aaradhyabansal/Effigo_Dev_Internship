
FROM eclipse-temurin:22-jdk-jammy


WORKDIR /app


COPY target/POC-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
