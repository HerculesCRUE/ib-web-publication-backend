# Compilación

A continuación se indican los pasos que hay que seguir para llevar a cabo la generación del artefacto.

## Prerrequisitos

Se precisa disponer los siguientes elementos configurados:

* OpenJDK 11
* Maven 3.6.x

## Compilación

Se ejecuta el siguiente comando para realizar la compilación :

```bash
mvn clean package
```

También es posible instalar o desplegar los artefactos sustituyendo `package` por `install` o `deploy` respectivamente.

Los artefactos se generan dentro del directorio `target`:

* Artefacto: management-system-{version}.jar
