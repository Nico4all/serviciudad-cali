serviciudad_cali/
 └─ src/main/java/com/serviciudad/serviciudad_cali/
     ├─ adapters/
     │   ├─ inbound/rest/
     │   │   ├─ ConsultaUnificadaController.java
     │   │   ├─ EnergiaController.java
     │   │   └─ AcueductoController.java
     │   └─ outbound/
     │       ├─ energia/
     │       │   └─ AdaptadorArchivoEnergia.java
     │       └─ acueducto/
     │           └─ entity/
     │               └─ FacturaAcueductoEntity.java
     │           └─ AcueductoRepositoryAdapter.java
     │           └─ SpringDataAcueductoRepository.java
     ├─ application/service/
     │   └─ ConsultarFacturasAcueductoService.java
     │   └─ ConsultaUnificadaService.java
     └─ domain/
         ├─ model/
         │   ├─ FacturaEnergia.java
         │   ├─ FacturaAcueducto.java
         │   └─ FacturaUnificada.java
         └─ port/
             ├─ EnergiaRepositoryPort.java
             └─ AcueductoRepositoryPort.java
     └─ src/main/resources/
         └─ data/
            └─ consumos_energia.txt
         └─ static/
            └─ index.html
.env
.gitignore
Dockerfile
docker-compose.yml
README.md
mvnw
mvnw.cmd
pom.xml