FROM eclipse-temurin:22-jdk-jammy

WORKDIR /app

COPY target/GMA2-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "app.jar"]