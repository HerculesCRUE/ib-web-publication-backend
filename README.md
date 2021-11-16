![](./images/logos_feder.png)

| Entregable     | **Servicio de publicación web**                              |
| -------------- | ------------------------------------------------------------ |
| Fecha          | 03/11/2021                                                   |
| Revisado por   | Álvaro Guitierrez Blanco                                          |
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
| `APP_KAFKA_IMPORT_ERROR_TOPIC_NAME` | Nombre del topic de Kafka para errores de importación | import-error | 
| `SPRING_KAFKA_BOOTSTRAP_SERVERS` | URL del servicio de Kafka para los productores | localhost:29092 |
| `SPRING_KAFKA_CONSUMER_BOOTSTRAP_SERVERS` | URL del servicio de Kafka para los consumidores | localhost:29092 |    
| `APP_LDP_URINAMESPACE` | Namespace generado para almacenar las entidades en trellis. Se utiliza al consultarlas en el apartado LDP | http://hercules.org/um/es-ES/rec  | 
| `APP_LDP_INVALIDENTITIES` | Listado de entidades que no aparecerán ni en el listado de entidades del grafo, ni los listados de entidades relacionadas para los detalles de una entidad. Las entidades deberán estar separadas por el carácter ','| Researcher-position, Researcher-role, Research-group, Phd-supervising-relationship, Supervisee-role, Feature, Project-contract, Funding, Research-field, Awarded-degree, Funding-source, Funding-program, Academic-subject  |
| `APP_LDP_VALIDPROPERTIES` | Listado de propiedades que se evalúan a la hora de resolver el campo descripción en los listados de entidades relacionadas para los detalles de una entidad. Se evaluarán en el mismo orden en que aparecen en el listado, es decir, si una entidad tienen título y descripción, se mostrará el valor de título. Las propiedades deberán estar separadas por el carácter ',' | title, description, name, correspondingAuthor, id  |  
| `APP_LDP_FILTEREDDETAILSPROPERTIES` | Listado de propiedades que se filtran dentro de los detalles de una entidad. Adicionalmente no se mostrarán de facto las propiedades que tengan valores vacíos o con la cadena 'null'. Las propiedades deberán estar separadas por el carácter ',' | localId, id, name  | 
| `APP_ENTITIES` | Al mostrar los detalles de una entidad, se muestras dons listado de entidades relacionadas, que se referencian en esta documentación con 'forward' (Relaciones principales)   y como 'back' (Entidades relacionadas). Por defecto, para las 'forward' se mostrarán siempre las siguientes relaciones directas hacia adelante, menos las que estén filtradas por 'APP_LDP_INVALIDENTITIES'. De la misma forma, para las 'back', se mostrarán siempre las siguientes relaciones directas hacia atrás, menos las que estén filtradas por 'APP_LDP_INVALIDENTITIES'. A mayores, se puede configurar en cada una de esas dos secciones, rutas a otros nodos que tengan algún salto. Para ello se  añade a este array. * type: Será el listado de entidades para el que aplica la configuracion. * forward y back. Listado de enlaces que se seguirán para llegar al nodo final y mostrarlos en 'Relaciones principales' y 'Entidades relacionadas' respectivamente . Por ejemplo para Person, inheresIn:back\|relates:back nos lleva hasta Project, pasando por 'ResearcherRole'  |dd-MM-yyyy  | 

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
