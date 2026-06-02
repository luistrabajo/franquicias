# ===== ETAPA 1: BUILD =====
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar pom.xml
COPY pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar aplicación
RUN mvn clean package -DskipTests


# ===== ETAPA 2: RUNTIME =====
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar JAR generado
COPY --from=build /app/target/*.jar app.jar

# Puerto Spring Boot
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]