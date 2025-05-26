# Como probar

Requisitos JAVA_21 superior
Ajusta tu env

setx JAVA_HOME C:\Users\oskar\.jdks\openjdk-24.0.1\bin

# Schema

Las tablas se crea por si solas, a partir del schema.sql dentro de src/main/resources
No debe realizarse ningun paso adicional.

# Instalacion de Maven. Ejecucion de Tests.

Ejecutar ./mvnw test

Esto instalara maven si no lo tenes.

# Correr la applicacion

Ejecutar ./mvnw bootRun

Utilizar Postman para ejecutar el
POST a http://localhost:8080/usuarios

´
curl --location 'http://localhost:8080/usuarios' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2",
"phones": [
{
"number": "1234567",
"cityCode": "1",
"countryCode": "57"
}
]
}'
´

Si lo ejecutas por segunda vez deberias obtener error.

# Abrir consola H2

http://localhost:8080/h2-console

Buscar en Log el nombre de la BD

´H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:536b0f8f-cd42-4e04-9ac4-32dd29d6305c'´

En este caso estos el connection string es:

jdbc:h2:mem:084f8593-8b72-4b25-a56a-02e11155578d