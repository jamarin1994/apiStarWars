API de Guerra de las Galaxias
Este proyecto es un desafío para implementar una API usando REST para gestionar datos de films según swapi .

Aquí encontrará un proyecto API de Start Wars que utiliza Java, Spring Boot y MongoDB para este propósito de solución.

Requisitos:
La API debe ser REST;

Para cada pelicula, se deben obtener los siguientes datos de la base de datos de la aplicación e ingresarlos manualmente:

episode_id
title
release_date

Para cada pelicula se pueden obtener a través de la API pública de Star Wars: https://swapi.dev/api/films/


Descripción:
Dado que este es el proyecto java maven spring boot, necesita que la JVM esté instalada en su máquina para poder ejecutarse. También debe proporcionar una base de datos Mongo en funcionamiento para la aplicación, ya que es una API REST que realiza lecturas y escrituras en una base de datos Mongo que se puede configurar en el archivo application.properties cambiando el valor de las propiedades spring.data.mongodb.uriy . spring.data.mongodb.databaseLos valores predeterminados para estos valores son mongodb://localhost:27017y starwarsrespectivamente, pero puede cambiarlos si es necesario.

También hay un archivo application-test.properties que se puede cambiar para ejecutar las pruebas en caso de que desee utilizar una base de datos diferente en sus pruebas. Para ejecutar las pruebas, simplemente use el comando mvn clean verifyy vea el resultado de las pruebas.

Para ejecutar este proyecto existe una clase Boot.java que tiene un método principal, simplemente ejecútelo o use el comando maven: mvn clean compile spring-boot:runy vea los resultados en http://localhost:8080/

Para empaquetar la aplicación, ejecute el comando mvn clean packagey vea el archivo de salida starwarsapi-1.0.0.jaren la carpeta de destino. Para ejecutar la api con el jar generado solo use el comandojava -jar starwarsapi-1.0.0.jar

Nota
Hay una documentación proporcionada por Swagger en /swagger-ui.html.