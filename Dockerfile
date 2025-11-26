# Stage 1: Build
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copiar pom y mvnw para cache de dependencias
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Dar permiso de ejecución a mvnw
RUN chmod +x mvnw

# Descargar dependencias offline
RUN ./mvnw dependency:go-offline

# Copiar código y compilar
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar solo el JAR generado
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Variables de entorno
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-prod}

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]