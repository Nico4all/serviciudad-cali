# Informe: Arquitectura Hexagonal y Principios de Diseño

## **Pregunta 1: Roles y Patrones**

¿Cómo se denomina, dentro de la arquitectura hexagonal, a la interfaz que define la operación que el dominio (Core) espera que un sistema externo (como la base de datos) implemente, y qué patrón de diseño GoF se asemeja a esta función?

En la arquitectura hexagonal (también conocida como *Ports and Adapters*), la interfaz que define la operación que el dominio (Core) espera que un sistema externo implemente se denomina **puerto de salida** o **Driven Port**.  

Este puerto representa una abstracción que el núcleo de la aplicación (el dominio) define para interactuar con servicios externos, como bases de datos, sistemas de mensajería o APIs. El dominio no conoce los detalles de implementación de estos servicios; solo establece las funcionalidades que necesita, mediante interfaces.  

El patrón de diseño GoF que más se asemeja a este mecanismo es el **Patrón Adapter**, ya que permite traducir la interfaz de un sistema externo para que encaje con la interfaz esperada por el dominio.  
El adaptador implementa la interfaz del puerto (definida por el dominio) y delega las operaciones en la tecnología externa correspondiente. En este contexto, **el adaptador actúa como un puente entre el puerto y la implementación concreta**, garantizando la desacoplación entre el núcleo del negocio y las dependencias externas.

---

## **Pregunta 2: Aislamiento y Dependencias**

En el contexto de este diseño, ¿hacia dónde apunta la dependencia de código entre un Adaptador de Base de Datos (por ejemplo, el que usa JPA para PostgreSQL) y el Puerto Secundario (Driven Port) que implementa?  

En la arquitectura hexagonal, la **dirección de las dependencias** es un principio esencial para garantizar el aislamiento del dominio respecto a la infraestructura técnica.  

Cuando analizamos la relación entre un **Adaptador de Base de Datos** (por ejemplo, uno que usa JPA para conectarse a PostgreSQL) y el **Puerto Secundario (Driven Port)** que implementa, **la dependencia apunta desde el adaptador hacia el puerto**, y no al revés.  

Esto significa que el adaptador depende de la interfaz definida por el dominio.  
El **Puerto Secundario** es una interfaz declarada dentro del núcleo del dominio que especifica las operaciones que este necesita del mundo exterior (guardar, consultar, eliminar entidades, etc.), sin importar cómo se implementan.  

La implementación concreta —el adaptador JPA— reside fuera del dominio, en la capa de infraestructura.  

Este principio refleja el **Principio de Inversión de Dependencias (DIP)**, parte de la arquitectura limpia y del diseño orientado a interfaces.  
Según este principio:  
- Los módulos de alto nivel (dominio) **no deben depender** de los módulos de bajo nivel (infraestructura).  
- Ambos deben depender de **abstracciones**.  

En la práctica, el dominio define la interfaz (puerto) y los adaptadores concretos la implementan, dirigiendo la dependencia hacia el dominio.  
Gracias a esto, el dominio se mantiene independiente de tecnologías como JPA, Hibernate o PostgreSQL.  

En conclusión, **el flujo de dependencia apunta desde el Adaptador de Base de Datos hacia el Puerto Secundario (Driven Port)**, preservando la independencia, testabilidad y flexibilidad del núcleo de negocio dentro de la arquitectura hexagonal.

---

## **Pregunta 3: Flexibilidad y Rol**

Si se añade un nuevo Adaptador (Adapter) para integrar el microservicio con una cola de Kafka como fuente de nuevos comandos de pedido, ¿qué rol funcional (Primario/Driving o Secundario/Driven) asume este Adaptador dentro de la Arquitectura Hexagonal?  

En la arquitectura hexagonal, cada componente cumple un rol según la dirección del flujo de control hacia el núcleo del sistema.  

Al agregar un adaptador que conecta el microservicio con una **cola de Kafka** como fuente de nuevos comandos, este **asume el rol de Adaptador Primario (Driving Adapter)**.  

El adaptador primario es el encargado de **iniciar la comunicación con el dominio**, reaccionando ante eventos o solicitudes externas y traduciéndolos en acciones que el dominio puede procesar.  
El dominio no conoce las fuentes externas que lo activan; simplemente define las operaciones que pueden iniciarse a través de **puertos de entrada**.  

En este caso, la cola de Kafka genera eventos o comandos que impulsan la lógica del negocio. El adaptador consume los mensajes, los transforma en estructuras compatibles con el dominio y llama al **Puerto de Entrada** correspondiente.  
Así, el adaptador funciona como un traductor entre el entorno externo asincrónico (Kafka) y la lógica del dominio.

Este enfoque ofrece **flexibilidad y mantenibilidad**, permitiendo reemplazar mecanismos de entrada (Kafka, RabbitMQ, HTTP, gRPC, etc.) sin modificar el dominio.  
El dominio solo conoce las **interfaces (puertos)** que representan sus capacidades de entrada, manteniéndose independiente de las tecnologías externas.  

En resumen, el adaptador que integra Kafka cumple el rol de **Adaptador Primario (Driving Adapter)**, ya que impulsa la ejecución del dominio mediante la recepción de eventos externos. Su función es canalizar los mensajes del entorno hacia el núcleo del negocio, a través de un puerto de entrada definido, garantizando la independencia tecnológica y la evolución sin fricciones.

---
