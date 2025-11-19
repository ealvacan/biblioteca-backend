# Etapa 1: Construir la app con Maven Wrapper
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Construir jar
RUN mvn -q -e -DskipTests clean package

# Etapa 2: Runtime (ejecutar el jar)
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar jar generado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
