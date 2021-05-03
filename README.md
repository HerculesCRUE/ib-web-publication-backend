![](./images/logos_feder.png)

| Entregable     | **Servicio de publicación web**                              |
| -------------- | ------------------------------------------------------------ |
| Fecha          | 03/05/2021                                                   |
| Revisado por   | Alejandro Torrecilla Sanchez                                           |
| Proyecto       | [ASIO](https://www.um.es/web/hercules/proyectos/asio) (Arquitectura Semántica e Infraestructura Ontológica) en el marco de la iniciativa [Hércules](https://www.um.es/web/hercules/) para la Semántica de Datos de Investigación de Universidades que forma parte de [CRUE-TIC](https://www.crue.org/proyecto/hercules/) |
| Módulo         | Servicio e publicación web - API                             |
| Tipo           | Software                                                     |
| Objetivo       | Arquetipo Java para API Rest con base de datos relacional.   |
| Estado         | **100%** Todos los servicios han sido desarrollados para front |


# ASIO - Web publication backend

|     | Master |
| --- | ------ |
| Quality Gate | [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=HerculesCRUE_ib-web-publication-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=HerculesCRUE_ib-web-publication-backend) |
| Coverage | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=HerculesCRUE_ib-web-publication-backend&metric=coverage)](https://sonarcloud.io/dashboard?id=HerculesCRUE_ib-web-publication-backend) |

Sistema de gestión de datos del módulo de procesamiento para el proyecto Backend SGI (ASIO).

## OnBoarding

Para iniciar el entorno de desarrollo se necesitan cumplir los siguientes requisitos:

- OpenJDK 11
- Eclipse JEE 2019-09 con plugins:
  - Spring Tools 4
  - m2e-apt
  - Lombok
- Docker

## Metodología de desarrollo

La metodología de desarrollo es Git Flow.

## Entorno de desarrollo Docker

La inicialización de los elementos adicionales al entorno de desarrollo se realiza con docker.

En el directorio docker-devenv se ha configurado un fichero docker-compose.yml para poder arrancar el entorno de desarrollo.

Para arrancar el entorno:

```bash
docker-compose up -d
```

Para pararlo:

```bash
docker-compose down
```

## Instalación en entorno real

Es preciso configurar las siguientes variables de entorno cuando se instale en un entorno real:

| Variable                | Descripción         | Valor por defecto                    |
| ----------------------- | ------------------- | ------------------------------------ |
| `APP_FUSEKITRELLIS_URL` | Url endpoint sparql | http://localhost:3030/trellis/sparql |
| `APP_FEDERATION_SERVICES` | Si es *true* indica que se van a usar los servicios implementados en el proyecto federation. Si es *false* indica que se llama directamente a fuseki. | false |
| `APP_FEDERATIONALL_URL` | Url endpoint sparql consulta federada | http://localhost:9328/federation/nodes/all |
| `APP_FEDERATIONNODE_URL` | Url endpoint sparql consulta federada node | http://localhost:9328/federation/nodes/listl |
| `KEYCLOAK_AUTH_SERVER_URL` | Url endpoint KEYCLOAK | http://localhost:8443/auth |
| `APP_PERSISTENCE_DATASOURCE_URL` | Url base de datos | jdbc:mysql://localhost:3307/umasio?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&createDatabaseIfNotExist=true |
    

### Ejecución

Al generarse un JAR bootable la ejecución se realiza mediante el siguiente comando:

```bash
java -jar {jar-name}.jar
```

Sustituyendo `{jar-name}` por el nombre del fichero JAR generado.

No es necesario especificar la clase de inicio de la aplicación, ya que el fichero MANIFEST.MF generado ya contiene la información necesaria. Solamente se especifican los parametros necesarios.

## Testing y cobertura

Se incluyen los resultados del testing y cobertura en los siguientes enlaces:

* [Testing](https://reports.herculesasioizertis.desa.um.es/web-publication-backend/surefire/surefire-report.html)
* [Cobertura](https://sonarcloud.io/component_measures?id=HerculesCRUE_ib-web-publication-backend&metric=coverage&view=list)
* [Testing BDD](docs/testing.md)

## Documentación adicional

- [Compilación](docs/build.md)
- [Generación Docker](docs/docker.md)
