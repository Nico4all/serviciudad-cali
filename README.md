Integrantes:
Esteban Nicolas Sanchez Tobar- 22506731
Alejandro Muñoz Franco- 22506704
Andres Felipe Gonzalez Ortegon- 2226012




#Instrucciones para montaje del proyecto.
## 1. Clonar repositorio
### git clone https://github.com/Nico4all/serviciudad-cali
## 2. Verificar instalación del docker desktop (Si no esta instalado Instalarlo)
## 3. Crear red compartida en docker (Terminal)
### docker network create shared_net
## 4. Hacer el build dentro de la carpeta raiz donde clono el archivo
### docker compose up --build -d
## 5. Verificar que todos los contenedores esten encendidos

Al hacer el build directamente se carga a la base de datos llamada serviciudad_db y el archivo txt ubicado en "serviciudad-cali\src\main\resources\data".
Si desea agregar mas datos al servicio de energia debe modficar el archivo consumos_energia.txt y agreagar nuevos datos.
Luego de agregar los datos tiene que realizar nuevamente el build.
Para el servicio de acueducto debe iniciar el gestor de la base de datos con la ruta http://localhost:8081/ ahi encontrara la base de datos y la tabla facturas_acueduto .

Nota: Tanto el servicio de energia como el servicio de acueducto ya tienen datos preestablecidos, en caso de querer cambiarlos debera modificarlos en
Energia: "serviciudad-cali\src\main\resources\data\consumos_energia.txt"
Acueducto: "serviciudad-cali\bin\config\initdb\01_init_serviciudad.sql"


Nota 2: Las rutas expuestas en el endpoint son: 

http://localhost:8080/ (Para verificar la disponibilidad de la api)
http://localhost:8080/energia/0009876543 (Para verificar los datos de energia asociados a ese ID)
http://localhost:8080/acueducto/0009876543 (Para verificar los datos de acueducto asociados a ese ID)
http://localhost:8080/servicios/0009876543 (Para verificar los datos unificados a ese ID)
http://localhost:8080/index.html  (Vista para consultar los datos por ID)

ID de los Servicios:
0001234567
0009876543
0001122334

Colección Postman:
https://nico4all.postman.co/workspace/Nico4all's-Workspace~4b07b55d-55f2-4953-8a67-012ba59593ad/collection/44892135-f023824f-2388-4950-8b62-ee42f398dd0d?action=share&creator=44892135

