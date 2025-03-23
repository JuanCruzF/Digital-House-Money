🚀 Digital Money House – Documentación de Servicios

🔹 registrationUserService
Este servicio gestiona el registro de usuarios en el sistema de identidad Keycloak, permitiendo un control eficiente sobre la autenticación, autorización y gestión de sesiones dentro de Digital Money House.

🔹 Se comunica de forma sincrónica con userService para:
✅ Registrar al usuario en la base de datos.
✅ Generar su CVU (Clave Virtual Uniforme).
✅ Habilitar el uso de la billetera virtual.

🔐 Keycloak
Keycloak es el sistema de gestión de identidad y acceso (IAM) utilizado en Digital Money House. Proporciona:
🔹 Autenticación y autorización seguras.
🔹 Gestión centralizada de sesiones.
🔹 Protección de accesos a los servicios.

🌐 Gateway
El Gateway es el punto de entrada centralizado para todas las solicitudes dirigidas a los servicios de Digital Money House. Implementa el patrón Edge Server y se encarga de:
🔹 Administrar el tráfico entre clientes y microservicios.
🔹 Enrutar y balancear las solicitudes.
🔹 Aplicar medidas de seguridad y control de acceso.

🗄 userService
Servicio responsable de gestionar las funcionalidades principales de Digital Money House mediante una API REST conectada a PostgreSQL. Se encarga de:
🔹 Administrar la información de los usuarios.
🔹 Ejecutar operaciones relacionadas con la billetera digital.
🔹 Garantizar la persistencia de datos de forma eficiente.

📡 eurekaServer
Implementa el patrón Service Registry o Service Discovery, permitiendo:
🔹 Registrar automáticamente los microservicios.
🔹 Descubrir dinámicamente otros servicios en el entorno.
🔹 Facilitar la escalabilidad y la comunicación entre servicios.

⚙️ configServer
Este servicio sigue el patrón Central Configuration, proporcionando un repositorio centralizado para la configuración de aplicaciones y servicios. Beneficios:
🔹 Centraliza la configuración de todos los servicios.
🔹 Facilita actualizaciones sin necesidad de reiniciar aplicaciones.
🔹 Mejora la consistencia y flexibilidad del sistema.


## 📑 Pruebas y Documentación  

📌 **Diagrama de API:** [🔗 Ver en Google Sheets](https://drive.google.com/file/d/1g5Au9CfiJXP4pHIe6ozmmBm-YXzjF5W3/view?usp=sharing)  

📌 **Testing** [🔗 Ver en Google Sheets](https://drive.google.com/file/d/1LS1Q9R0LHSLOWt06Mdn5dcFRjaYHafxP/view?usp=sharing)  

📌 **Colección de Postman:** [🔗 Acceder en Postman](https://www.postman.com/juan55-1636/workspace/digital-house-money/collection/40877067-f76542fe-4640-4356-93c0-98a699a19894?action=share&source=copy-link&creator=40877067)  


# 🚀 Sprint I  

> 📝 **Historia de Usuario:**  
> "Como usuario quiero registrarme en Digital Money House para acceder y usar los servicios que ofrece."

El servicio **`registrationUserService`** se encarga del registro de usuarios en nuestro **IAM (Keycloak)**, permitiendo manejar sesiones y aprovechar sus funcionalidades de autenticación y autorización.  

Para registrar un usuario, se debe realizar una solicitud `POST` al endpoint del **Gateway**:  

📌 `http://localhost:9090/registration` (No requiere autenticación).  

Ejemplo de registro en **Postman:**  
[![Registro.png](https://i.postimg.cc/Dw9WCb4C/Registro.png)](https://postimg.cc/Wd7bzznr)

Al registrar un usuario:  
✅ Se devuelve un `status 200` junto con los datos del usuario (sin ID ni contraseña).  
✅ Los campos `cvu` y `alias` se generan aleatoriamente según los requisitos.  

🔹 **Usuarios en Keycloak:**  
[![registro.png](https://i.postimg.cc/jSDhBNgP/registro.png)](https://postimg.cc/rDLWrdLw)

✅ **Seguridad:** La contraseña no se almacena en la base de datos, ya que Keycloak gestiona las credenciales.  
✅ **Username:** Se usa el email del usuario para iniciar sesión en la billetera.  

🔹 **Autenticación y Token de Acceso:**  
[![Credenciales-validas.png](https://i.postimg.cc/BZr0ycnf/Credenciales-validas.png)](https://postimg.cc/Jt5vHXXp)
[![session.png](https://i.postimg.cc/W16qt1Ht/session.png)](https://postimg.cc/XrqYz430)

> 📝 **Historias de Usuario:**  
> - "Como usuario quiero acceder a Digital Money House para realizar transferencias de fondos."  
> - "Como usuario necesito poder cerrar sesión en la billetera Digital Money House."  

📌 **Logout:** Se puede cerrar sesión enviando una solicitud a:  
`http://localhost:8080/realms/digital-money-house/protocol/openid-connect/logout`  

---

# 🚀 Sprint II  

> 📝 **Historia de Usuario:**  
> "Como usuario necesito ver, en el inicio, la cantidad de dinero disponible y los últimos 5 movimientos realizados con la billetera Digital Money House."  

Para probar estos servicios, se usa **Swagger** en `userService`:  
📌 `http://localhost:8082/swagger-ui/index.html#/`  

🔹 **Autorización con Token:**  
[![swagger.png](https://i.postimg.cc/CMQCk7sD/swagger.png)](https://postimg.cc/w3DsdhPT)
[![swagger-auth.png](https://i.postimg.cc/Z5tVHBKj/swagger-auth.png)](https://postimg.cc/ykjmYNRS)

🔹 **Saldo y Movimientos del Usuario:**
[![account.png](https://i.postimg.cc/Hkbc0jVw/account.png)](https://postimg.cc/CRLxShC5)  


🔹 **Transacciones del Usuario:**  
[![transaction.png](https://i.postimg.cc/6QwKkpzf/transaction.png)](https://postimg.cc/75Bj7DF5)

> 📝 **Historia de Usuario:**  
> "Como usuario quiero ver mi perfil para consultar los datos de mi CVU y alias."

🔹 **Consulta de Perfil:**
[![userid.png](https://i.postimg.cc/QxzMVzrK/userid.png)](https://postimg.cc/Yh17sb0t)  

> 📝 **Historia de Usuario:**  
> - "Como usuario me gustaría ver una lista de mis tarjetas de crédito y débito disponibles."  
> - "Como usuario me gustaría agregar una tarjeta para cargar saldo o pagar servicios."  

🔹 **CRUD de Tarjetas:**  
[![CRUD.png](https://i.postimg.cc/x1qrZBby/CRUD.png)](https://postimg.cc/nX8wjR2C)

🔹 **Lista de Tarjetas del Usuario:**  
[![CardsReq.png](https://i.postimg.cc/Wz2pqSYW/CardsReq.png)](https://postimg.cc/S2vbwLhC)

> 📝 **Historia de Usuario:**  
> "Como usuario me gustaría eliminar una tarjeta que ya no uso."

🔹 **Eliminar Tarjeta:**  
[![eliminar-tarjeta.png](https://i.postimg.cc/VLmky46C/eliminar-tarjeta.png)](https://postimg.cc/8sXSMRLp)

---

# 🚀 Sprint III - IV  

> 📝 **Historia de Usuario:**  
> "Como usuario quiero ver toda la actividad de mi billetera para controlar mis transacciones."

🔹 **Historial de Actividades:**  
[![activities.png](https://i.postimg.cc/t4108Vh6/activities.png)](https://postimg.cc/WdPfkzYp)

> 📝 **Historia de Usuario:**  
> "Como usuario necesito el detalle de una actividad específica."

🔹 **Detalle de Actividad:**  
[![userid-transferid.png](https://i.postimg.cc/rFjW7D8q/userid-transferid.png)](https://postimg.cc/kBVBbX6H) 

> 📝 **Historia de Usuario:**  
> - "Como usuario quiero ingresar dinero desde mi tarjeta a mi billetera."  
> - "Como usuario quiero enviar dinero a un CBU/CVU/alias desde mi saldo."

🔹 **Transferencias y Carga de Saldo:**  
[![prueba-de-carga.png](https://i.postimg.cc/Sxjrr6cB/prueba-de-carga.png)](https://postimg.cc/rDXxytnj) 
[![prueba-de-carga-response.png](https://i.postimg.cc/fLdqvcQz/prueba-de-carga-response.png)](https://postimg.cc/R6MTSn0y)

📌 **Postman:** También puedes probar estos servicios a través de `Postman`, apuntando al **Gateway**:  
📌 `http://localhost:9090`  

---
