# --------- Etapa 1: Build ---------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar pom.xml y resolver dependencias
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Construir el JAR
RUN mvn -q -DskipTests package


# --------- Etapa 2: Runtime ---------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
