# Informe Técnico – Sistema de Consulta Unificada ServiCiudad Cali

## 1. Arquitectura General

La aplicación fue desarrollada siguiendo el enfoque de **Arquitectura Hexagonal (Ports & Adapters)**, con el objetivo de lograr **bajo acoplamiento**, **alta cohesión** y **facilidad de mantenimiento y evolución** del sistema.  
Esta arquitectura permite separar claramente la lógica del dominio de las dependencias externas (bases de datos, archivos, API REST, etc.).

### Estructura del Proyecto

serviciudad_cali/
└─ src/main/java/com/serviciudad/serviciudad_cali/
├─ adapters/
│ ├─ inbound/rest/
│ │ ├─ ConsultaUnificadaController.java
│ │ ├─ EnergiaController.java
│ │ └─ AcueductoController.java
│ └─ outbound/
│ ├─ energia/
│ │ └─ AdaptadorArchivoEnergia.java
│ └─ acueducto/
│ ├─ entity/
│ │ └─ FacturaAcueductoEntity.java
│ ├─ AcueductoRepositoryAdapter.java
│ └─ SpringDataAcueductoRepository.java
├─ application/service/
│ ├─ ConsultaUnificadaService.java
│ └─ ConsultarFacturasAcueductoService.java
└─ domain/
├─ model/
│ ├─ FacturaEnergia.java
│ ├─ FacturaAcueducto.java
│ └─ FacturaUnificada.java
└─ port/
├─ EnergiaRepositoryPort.java
└─ AcueductoRepositoryPort.java
└─ src/main/resources/
├─ data/
│ └─ consumos_energia.txt
└─ static/
└─ index.html


### Flujo General

1. El **usuario** accede a la interfaz web (frontend en `/resources/static/index.html`).
2. El **controlador `ConsultaUnificadaController`** recibe la solicitud con el número de documento.
3. El **servicio de aplicación (`ConsultaUnificadaService`)** coordina la obtención de datos de los servicios de energía y acueducto.
4. Los **adaptadores outbound** se encargan de comunicarse con los sistemas externos:
   - `AdaptadorArchivoEnergia`: lee y adapta los datos del archivo plano.
   - `AcueductoRepositoryAdapter`: accede a la base de datos a través de JPA.
5. El resultado se construye en un **objeto de dominio unificado (`FacturaUnificada`)**, que luego se transforma en un **DTO** y se devuelve como respuesta JSON al cliente.

---

## 2. Patrones de Diseño Implementados

### 1. Patrón **Adapter**

- **Archivo:** `AdaptadorArchivoEnergia.java`
- **Ubicación:** `adapters/outbound/energia`
- **Descripción:**  
  Este patrón se utilizó para conectar el sistema con un **componente legado** que entrega los datos de consumo de energía en un **archivo de texto plano de formato fijo**.  
  El adaptador lee el archivo `consumos_energia.txt` y **convierte cada línea en objetos del dominio (`FacturaEnergia`)** que la aplicación puede entender.

- **Justificación:**  
  Permite **aislar la lógica del dominio de los detalles técnicos del formato del archivo**, haciendo que el resto del sistema trabaje con objetos Java en lugar de cadenas de texto.  
  Si en el futuro el sistema de energía cambia a una API REST o base de datos, solo se debe modificar este adaptador sin afectar el resto de la aplicación.

---

### 2. Patrón **Builder**

- **Archivo:** `DeudaConsolidadaBuilder.java`
- **Ubicación:** `application/service` (o `domain/util`)
- **Descripción:**  
  Se implementa un **constructor paso a paso** para crear el objeto de salida `DeudaConsolidadaDTO`, el cual integra información proveniente de distintos orígenes (energía, acueducto, cliente, totales).

- **Justificación:**  
  El objeto final tiene múltiples campos calculados y fuentes heterogéneas.  
  El patrón **Builder** facilita su creación de manera legible y escalable, evitando constructores con demasiados parámetros y mejorando la claridad del código.

---

### 3. Patrón **DTO (Data Transfer Object)**

- **Archivos:**  
  - `DeudaConsolidadaDTO.java`  
  - `DetalleServicioDTO.java`
- **Ubicación:** `application/dto`
- **Descripción:**  
  Estos objetos representan la estructura **que se envía al frontend como JSON**, sin exponer directamente las entidades de dominio ni los objetos internos.

- **Justificación:**  
  Mejora la **seguridad y encapsulación** del sistema, desacoplando el modelo interno de los datos que se exponen externamente.  
  Además, facilita el mapeo y la compatibilidad con otras posibles APIs o clientes.

---

### 4. Patrón **Repository (Spring Data JPA)**

- **Archivos:**  
  - `SpringDataAcueductoRepository.java`  
  - `AcueductoRepositoryAdapter.java`
- **Ubicación:** `adapters/outbound/acueducto`
- **Descripción:**  
  Spring Data JPA implementa el patrón Repository de forma nativa.  
  La interfaz `SpringDataAcueductoRepository` extiende `JpaRepository`, y el adaptador `AcueductoRepositoryAdapter` traduce las llamadas del dominio al repositorio.

- **Justificación:**  
  El patrón **Repository** abstrae el acceso a datos, evitando código repetitivo (CRUD) y desacoplando la lógica de negocio de los detalles del ORM o de la base de datos (en este caso, MariaDB).  
  Si cambia la tecnología de persistencia, el dominio permanece intacto.

---

### 5. Patrón **Inversión de Control / Inyección de Dependencias (IoC - DI)**

- **Aplicado en:**  
  - `@RestController` (controladores)  
  - `@Service` (servicios de aplicación)  
  - `@Repository` (adaptadores de persistencia)
- **Descripción:**  
  Spring Boot maneja automáticamente la creación y gestión de instancias de clases mediante su contenedor de dependencias.

- **Justificación:**  
  Este patrón es fundamental para mantener un **bajo acoplamiento** y **alta cohesión**.  
  Por ejemplo, los servicios se inyectan en los controladores y los repositorios se inyectan en los servicios, sin que una clase cree directamente instancias de otras.  
  Esto facilita la **prueba unitaria**, la **sustitución de implementaciones** y la **extensibilidad del sistema**.

---

## 3. Conclusiones

- La aplicación sigue el **principio de inversión de dependencias** de la arquitectura hexagonal: el dominio no depende de detalles externos, sino que define interfaces (ports) que los adaptadores implementan.
- Los patrones implementados contribuyen a una **arquitectura modular, extensible y mantenible**.
- Spring Boot, junto con JPA y los adaptadores, permite una integración fluida entre diferentes tecnologías (archivos planos, base de datos y frontend web).
- El resultado es un **monolito bien estructurado**, preparado para evolucionar hacia un sistema distribuido (microservicios) si se requiere en futuras versiones.
