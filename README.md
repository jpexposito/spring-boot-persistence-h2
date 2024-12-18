<div align="justify">

# <img src=images/spring-logo.png width="40"> Construcción de Servicios con Spring

<div align="center">
   <img src=images/users.png width="400">
</div>

## <img src=images/crud.png width="40"> CRUD de Usuarios en Java y Spring Boot

Este proyecto implementa una API RESTful utilizando **Java** y **Spring Boot** para gestionar usuarios. El objetivo es permitir la creación, lectura, actualización y eliminación de registros de usuarios, mediante los siguientes endpoints:

- **CRUD de usuarios**
  - **Crear usuario**: `POST /api/v1/add/user/`
  - **Leer usuario**: `GET /api/v1/user/{id}`
  - **Leer todos los usuario**: `GET /api/v1/users/`
  - **Actualizar usuario**: `PUT /api/v1/update/user/{id}`
  - **Eliminar usuario**: `DELETE /api/v1/delete/user/{id}`

La aplicación usa la gestión de excepciones mediante la clase `ResourceNotFoundException` para manejar casos donde un usuario no es encontrado. Además, la API está documentada con **Swagger** para facilitar la interacción.

Para arrancarlo debemos de ejecutar:

```console
mvn clean spring-boot:run
```

## Documentación OpenAPI (Swagger UI)

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