
FROM eclipse-temurin:22-jdk-jammy


WORKDIR /app


COPY target/payload-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
