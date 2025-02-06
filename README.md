<div align="justify">

# <img src=images/spring-logo.png width="40"> Construcción de Servicios con Spring

<div align="center">
   <img src=images/users.png width="400">
</div>

## <img src=images/crud.png width="40"> CRUD de Usuarios en Java y Spring Boot

Este proyecto implementa una API RESTful utilizando **Java** y **Spring Boot** para gestionar usuarios. El objetivo es permitir la creación, lectura, actualización y eliminación de registros de usuarios, mediante los siguientes endpoints:

## Arranque/parada del servicio

Para arrancarlo debemos de ejecutar:

```console
mvn clean spring-boot:run
```

### Arrancar con modo debug activado

Para arrancar spring-boot para debug en remote debemos hacerlo de la siguiente manera

```console
mvn clean spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

## Gestión de Usuarios

### Rest CRUD

- **Crear usuario**: `POST /api/v1/add/user/`
- **Leer usuario**: `GET /api/v1/user/{id}`
- **Leer todos los usuario**: `GET /api/v1/users/`
- **Actualizar usuario**: `PUT /api/v1/update/user/{id}`
- **Eliminar usuario**: `DELETE /api/v1/delete/user/{id}`

La aplicación usa la gestión de excepciones mediante la clase `ResourceNotFoundException` para manejar casos donde un usuario no es encontrado. Además, la API está documentada con **Swagger** para facilitar la interacción.

### Servicio Soap (CXF) de usuarios

- **Obtener todos los usuario**: `getAllUsers()`
- **Obtener un usuario por id**: `getUserById()`

El servicio **soap** quedara expuesto en la url [http://localhost:8080/services/users?wsdl](http://localhost:8080/services/users?wsdl).

- <img src=images/soap_url.png width="400">

Si queremos ver todos los servicios **cxf** expuestos debemos de visitar la siguiente [url](http://localhost:8080/services).

## <img src=images/soapui.png width="40"> Consumo de servicios a través de SoapUi

SOAP UI es una herramienta de pruebas y desarrollo para servicios web que permite probar, inspeccionar y consumir servicios **SOAP** y **RESTful** de manera fácil y efectiva.

### Descarga e instalación de SoapUi

La descarga y la instalación de SoapUi se puede realizar a través del siguiente [enlace](https://www.soapui.org/getting-started/installing-soapui/).

### Crear un proyecto con SoapUi

- **Abrir SOAP UI y seleccionar File** -> ***New SOAP Project*** (para servicios SOAP) o ***New REST Project*** (para servicios REST).
  
  <img src=images/soapui_new_proyect.png width="200">
- Para SOAP: Ingresar la URL del WSDL del servicio y darle un nombre al proyecto.
- Para REST: Ingresar la URL base del servicio REST y definir el nombre del proyecto.
- SOAP UI generará automáticamente los métodos disponibles en el servicio, permitiéndote crear y ejecutar peticiones de prueba.

### Consumo de métodos del servicio

Una vez creado el proyecto en soapui para consumir el servicio, podremos obtener los siguientes resultados:

- getAllUsers
  - Petición
  
  ```xml
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:spr="springboot.soap.service">
    <soapenv:Header/>
    <soapenv:Body>
        <spr:getAllUsers/>
    </soapenv:Body>
  </soapenv:Envelope>
  ```
  - Respuesta

  ```xml
  <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:getAllUsersResponse xmlns:ns2="springboot.soap.service">
          <user>
              <id>1</id>
              <name>Manuel</name>
          </user>
          <user>
              <id>2</id>
              <name>Pedro</name>
          </user>
        </ns2:getAllUsersResponse>
    </soap:Body>
  </soap:Envelope>
  ```

- getUserById
  - Petición
  
  ```xml
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:spr="springboot.soap.service">
    <soapenv:Header/>
    <soapenv:Body>
        <spr:getUserById>
          <userId>1</userId>
        </spr:getUserById>
    </soapenv:Body>
  </soapenv:Envelope>
  ````
  
  - Respuesta
  
  ```xml
  <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:getUserByIdResponse xmlns:ns2="springboot.soap.service">
          <return>
              <id>1</id>
              <name>Manuel</name>
          </return>
        </ns2:getUserByIdResponse>
    </soap:Body>
  </soap:Envelope>
  ```

## Documentación OpenAPI Rest (Swagger UI)

- **URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> En el siguiente [enlace](https://github.com/jpexposito/code-learn/tree/main/segundo/pgv/5-red-servicos) se encuentra la documentación de creación y documentación de servicios en [Spring](https://spring.io/). El cliente para el consumo del servicio de forma programática se encuentra en el siguiente [enlace](https://github.com/jpexposito/spring-boot-persistence-h2-client).

## Configurar conexión de Spring Boot con H2

Para establecer la conexión con nuestra base de datos H2, es necesario indicarle a Spring Boot como conectarse, para ello lo hacemos a través del fichero application.yaml o application.properties.

Por defecto, Spring Boot nos va a configurar nuestra aplicación para conectarnos a nuestra base de datos en memoria H2 con username sa y la password vacía.

Aunque podemos parametrizar nuestra aplicación con los siguientes parámetros:

```properties
spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Habilitar la consola de BBDD H2
spring.h2.console.enabled: true

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
```

Hay que tener en cuenta que estamos usando una base de datos en memoria, por lo que cuando la aplicación finalice, la base de datos se borrara. Por lo que para poder persistir los datos de manera física vamos a añadir el siguiente parámetro:

```console
spring.datasource.url=jdbc:h2:file:/data/bbdd/h2
```

> Donde ***/data/bbdd/*** es un directorio que existe.

### Acceder a la interfaz gráfica de H2

La Base de Datos H2 tiene una interfaz gráfica a la que se accede a través del navegador, que funcionará como un gestor de base de datos, lo que nos permitirá ver tablas, roles y realizar queries. La consola de H2 no se encuentra activada por defecto en Spring, por lo que vamos a añadir el siguiente comando por configuración:

```properties
spring.h2.console.enabled=true
```

Una vez arranca la aplicación podemos acceder a la consola a través de este endpoint [http://localhost:8080/h2-console](http://localhost:8080/h2-console).

Donde la la url de la bbdd es:

```console
jdbc:h2:mem:testdb;MODE=MySQL
user=sa
password=password
```

<img src=images/h2-console1.png width="400">

<img src=images/h2-console2.png width="400">

</div>