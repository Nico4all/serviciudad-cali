# Imagen base de OpenJDK (compatible con Spring Boot y Java 21+)
FROM eclipse-temurin:21-jdk

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo Maven Wrapper o pom.xml
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn

# Descargar dependencias (esto se cachea para builds más rápidos)
RUN ./mvnw dependency:go-offline

# Copiar el resto del código fuente
COPY src ./src

# Compilar el proyecto
RUN ./mvnw clean package -DskipTests

# Copiar solo el JAR generado
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto donde corre la app
EXPOSE 8080

# Variables de entorno por defecto (se pueden sobrescribir en Render)
ENV SPRING_PROFILES_ACTIVE=prod

# Exponer el puerto donde corre la app
EXPOSE 8080

# Ejecutar el jar generado
ENTRYPOINT ["java", "-jar", "target/serviciudad-cali-0.0.1-SNAPSHOT.jar"]
