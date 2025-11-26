mvn clean test jacoco:report

mvn clean test 

mvn clean install



# Informe de Pipeline y Pruebas Unitarias – Serviciudad Cali

## 1. Resumen del proyecto

El proyecto **Serviciudad Cali** es un servicio backend desarrollado en Java 21 con Maven, que gestiona la información de facturación de energía y acueducto. El proyecto cumple con los requisitos de calidad y DevOps definidos para la asignatura de Ingeniería de Software II.

Estructura principal del proyecto:

```
.
└── SERVICIUDAD-CALI/
    ├── .github/workflows/          # Pipelines de CI/CD
    ├── src/                        # Código fuente y pruebas
    ├── test/                       # Pruebas unitarias
    ├── Dockerfile                  # Imagen de contenedor
    └── docker-compose.yml          # Despliegue local
```

---

## 2. Pipelines CI/CD

### 2.1 CI Pipeline (`.github/workflows/ci.yml`)

Este pipeline automatiza la compilación, pruebas unitarias, verificación de cobertura y empaquetado:

**Jobs principales:**

1. **build-test**

   * **Función:** Compila el proyecto, ejecuta pruebas unitarias y genera reporte de cobertura.
   * **Cobertura mínima:** 80%
   * **Servicios:** MariaDB 10.5 para pruebas de integración.
   * **Salida:** Artefactos de pruebas (`target/surefire-reports`) y reporte de cobertura (`target/site/jacoco/jacoco.xml`).

2. **package**

   * **Función:** Empaqueta el proyecto en un JAR.
   * **Salida:** Artefacto JAR subido a GitHub Actions (`serviciudad-jar-${{ github.sha }}`).

3. **docker-build**

   * **Función:** Construye la imagen Docker del backend.
   * **Etiquetas:** `latest` y `${{ github.sha }}`
   * **Salida:** Imagen Docker exportada como artefacto (`/tmp/image.tar`).

4. **security-scan**

   * **Función:** Escaneo de vulnerabilidades usando Trivy.
   * **Condición:** Solo para Pull Requests.
   * **Salida:** Archivo SARIF con resultados de vulnerabilidades.

5. **canary-deploy**

   * **Función:** Despliegue Canary del servicio usando Docker.
   * **Proceso:**

     * Descarga y carga de imagen Docker.
     * Etiquetado como `canary`.
     * Lanzamiento de contenedor paralelo (`serviciudad-canary`) en puerto 8081.
     * Prueba de disponibilidad con `/actuator/health`.

6. **promote-or-rollback**

   * **Función:** Promoción de la versión Canary a estable o rollback.
   * **Proceso:**

     * Descarga y carga de la imagen Docker.
     * Etiquetado de la imagen `canary` a `latest`.
     * Detención de contenedor estable previo (si existe) y despliegue de nuevo contenedor estable.
     * Opción de rollback a contenedor Canary si falla.

---

## 3. Pruebas Unitarias

El proyecto incluye una suite completa de pruebas unitarias que cubren la lógica de negocio y los adaptadores.

### 3.1 Cobertura

* Herramienta: **JaCoCo**
* Cobertura mínima exigida: **≥ 80%**
* Los reportes se generan automáticamente en `target/site/jacoco/index.html`.

### 3.2 Ubicación de las pruebas

```
src/test/java/com/serviciudad/serviciudad_cali/
├── adapters/
│   ├── inbound/rest/            # Controladores REST
│   └── outbound/                # Adaptadores de persistencia
├── application/service/         # Servicios de negocio
├── config/                      # Configuraciones específicas
├── domain/model/                # Entidades y modelos
├── dto/builders/                # Builders de DTO
└── util/                        # Utilidades
```

### 3.3 Ejecución

* Comando: `mvn -B clean verify`
* Variables de entorno para pruebas:

```bash
SPRING_DATASOURCE_URL=jdbc:mariadb://localhost:3306/serviciudad_test
SPRING_DATASOURCE_USERNAME=test_user
SPRING_DATASOURCE_PASSWORD=test_password
```

* Los tests incluyen:

  * Controladores REST (`AcueductoControllerTest`, `EnergiaControllerTest`, `ConsultaUnificadaControllerTest`)
  * Servicios de aplicación (`ConsultarFacturasAcueductoServiceTest`, `ConsultaUnificadaServiceTest`)
  * Adaptadores (`AdaptadorArchivoEnergiaTest`, `AcueductoRepositoryAdapterTest`, `SprinDataAcueductoRepositoryTest`)
  * Modelos y DTOs (`FacturaAcueductoTest`, `FacturaEnergiaTest`, `FacturaUnificadaTest`, `DeudaConsolidadaBuilderTest`)
  * Configuraciones y utilidades (`JacksonConfigTest`, `FixedWidthParserTest`, `ServiciudadCaliApplicationTests`)

---

## 4. Flujo de CI/CD

1. Commit/Push a `main` o `develop`.
2. Job `build-test` ejecuta compilación y tests.
3. Job `package` genera JAR y artefactos.
4. Job `docker-build` construye imagen Docker.
5. Job `canary-deploy` realiza despliegue Canary.
6. Job `promote-or-rollback` decide promoción a versión estable o rollback.
7. Reportes de cobertura y seguridad disponibles como artefactos del pipeline.

---

## 5. Conclusión

El proyecto **Serviciudad Cali** cumple con los criterios de calidad requeridos:

* Pruebas unitarias con cobertura ≥ 80%.
* Integración completa con pipeline CI/CD.
* Despliegue Canary funcional con posibilidad de promoción o rollback.
* Automatización de builds, pruebas, empaquetado y despliegue en Docker.
