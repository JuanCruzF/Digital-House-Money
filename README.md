🏦 Digital Money - API REST de Billetera Virtual

📌 Desafío Final - Back-End Developer | Digital House
Digital Money es una API REST desarrollada en Java con Spring Boot que permite gestionar una billetera virtual de manera segura y eficiente. Este proyecto incluye funcionalidades esenciales para la administración de usuarios, cuentas, tarjetas y operaciones bancarias.

🚀 Funcionalidades

🔹 Gestión de Usuarios
Registro de usuario con verificación de correo electrónico
Inicio y cierre de sesión
Modificación y eliminación de cuenta
Recuperación y cambio de contraseña

🔹 Gestión de Cuentas
Consulta de saldo e información de la cuenta
Edición del alias de la cuenta
Historial de transacciones
Descarga de comprobantes de transferencia en formato PDF

🔹 Gestión de Tarjetas
Creación y eliminación de tarjetas
Consulta de tarjetas registradas

🔹 Operaciones Bancarias
Depósitos a la cuenta
Transferencias entre cuentas

🛠️ Tecnologías Utilizadas
Java
Spring Boot
MySQL
Docker
Spring Security & JWT (Json Web Token)
Jakarta Mail (para gestión de correos)
iTextPDF (para generación de comprobantes)
JUnit & RestAssured (para pruebas automatizadas)

📑 Pruebas y Documentación
📌 Pruebas Exploratorias:
➡️ [Ver en Google Sheets](https://docs.google.com/spreadsheets/d/1mUobOhYdIC3WuVqAy4VpSEj5HkG-x6FX/edit?gid=1955779588#gid=1955779588)

📌 Pruebas Manuales:
➡️ [Ver en Google Sheets](https://docs.google.com/spreadsheets/d/1mUobOhYdIC3WuVqAy4VpSEj5HkG-x6FX/edit?gid=534667151#gid=534667151)

📌 Colección de Postman:
➡️ [Acceder en Postman](https://www.postman.com/docking-module-physicist-19257270/digital-house-money)



💻 Instalación y Ejecución

- git clone https://github.com/JuanCruzF/Digital-House-Money
- cd Digital-House-Money

Configurar credenciales de mailtrap en docker-compose.yml y en el application.properties

Construir la imagen de la aplicación:
- docker build -t digital-money-app .

Levantar los contenedores (API + MySQL):
- docker-compose up -d

Verificar que los contenedores están corriendo:
- docker ps


