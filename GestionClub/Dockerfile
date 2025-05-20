# Dockerfile (en GestionClub/)
# --- Fase de construcción con Maven ---
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package  # Genera el JAR en /app/target/

# --- Fase de ejecución ---
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/GestionClub-*.jar ./app.jar
EXPOSE 4000
ENTRYPOINT ["java", "-jar", "app.jar"]
