![](./images/logos_feder.png)

| Entregable     | **Servicio de publicación web**                              |
| -------------- | ------------------------------------------------------------ |
| Fecha          | 17/12/2020                                                   |
| Proyecto       | [ASIO](https://www.um.es/web/hercules/proyectos/asio) (Arquitectura Semántica e Infraestructura Ontológica) en el marco de la iniciativa [Hércules](https://www.um.es/web/hercules/) para la Semántica de Datos de Investigación de Universidades que forma parte de [CRUE-TIC](https://www.crue.org/proyecto/hercules/) |
| Módulo         | Servicio e publicación web - API                             |
| Tipo           | Software                                                     |
| Objetivo       | Arquetipo Java para API Rest con base de datos relacional.   |
| Estado         | **100%**  |
| Próximos pasos |       |

# ASIO - Web publication backend

|     | Master |
| --- | ------ |
| Quality Gate | [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=HerculesCRUE_ib-web-publication-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=HerculesCRUE_ib-web-publication-backend) |
| Coverage | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=HerculesCRUE_ib-web-publication-backend&metric=coverage)](https://sonarcloud.io/dashboard?id=HerculesCRUE_ib-web-publication-backend) |

Sistema de gestión de datos del módulo de procesamiento para el proyecto Backend SGI (ASIO).

## OnBoarding

Para iniciar el entorno de desarrollo se necesita cumplir los siguientes requisitos:

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

Será preciso configurar las siguientes variables de entorno cuando se instale en un entorno real:

| Variable                | Descripción         | Valor por defecto                    |
| ----------------------- | ------------------- | ------------------------------------ |
| `APP_FUSEKITRELLIS_URL` | Url endpoint sparql | http://localhost:3030/trellis/sparql |

### Ejecución

Al generarse un JAR bootable la ejecución se realizará mediante el siguiente comando:

```bash
java -jar {jar-name}.jar
```

Sustituyendo `{jar-name}` por el nombre del fichero JAR generado.

No es necesario especificar la clase de inicio de la aplicación, ya que el fichero MANIFEST.MF generado ya contiene la información necesaria. Solamente se especificarán los parametros necesarios.

## Testing y cobertura

Se incluyen los resultados del testing y cobertura en los siguientes enlaces:

* [Testing](http://herc-iz-front-desa.atica.um.es:8070/web-publication-backend/surefire/surefire-report.html)
* [Cobertura](https://sonarcloud.io/component_measures?id=HerculesCRUE_ib-web-publication-backend&metric=coverage&view=list)
* [Testing BDD](docs/testing.md)

## Documentación adicional

- [Compilación](docs/build.md)
- [Generación Docker](docs/docker.md)
