# tipo-cambio-api

Aplicación para el calculo de tipo de cambio.
Desarrollada en [Spring Boot](http://projects.spring.io/spring-boot/).
Por defecto el API soporta tipos de cambio de SOLES a DOLARES y de DOLARES A SOLES.

## Requerimientos

Para construir y correr la aplicación es necesario:

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

## Correr la aplicación localmente

- Correr la aplicación en cualquier IDE de preferencia que tenga soporte para Java y Spring Boot.

Para correr con docker:
- Ejecutar el siguiente comando docker-compose up --build


## Endpoints

- POST http://localhost:8080/api/v1/auth/login
  - Retorna el token JWT usando el siguiente payload:
  
    ```javascript
    {
      "usuario": "usuario",
      "contrasenha": "123"
    }
    ```
    
    Este usuario ha sido creado al inicializar la base de datos embebida.

- HEADERS:
  - Authorization: {JWT_TOKEN}

- GET http://localhost:8080/api/v1/tipo-cambio
  - Retorna los tipos de cambio soportados por el API.

- POST http://localhost:8080/api/v1/tipo-cambio
  - Guarda un nuevo tipo de cambio usando el siguiente payload:
  
    ```javascript
    {
      "monedaOrigen": "EUROS",
      "monedaDestino": "SOLES",
      "tipoCambio": 3.99
    }
    ```
    
- POST http://localhost:8080/api/v1/tipo-cambio/actualizar
    - Actualiza un tipo de cambio usando el siguiente payload:
  
      ```javascript
      {
        "monedaOrigen": "SOLES",
        "monedaDestino": "DOLARES",
        "tipoCambio": 2.234
      }
      ```
     
- POST http://localhost:8080/api/v1/tipo-cambio/calcular
   - Calcula el tipo de cambio según un monto, moneda origen y moneda destino:
  
     - Request:
     
     ```javascript
     {
       "monedaOrigen": "SOLES",
       "monedaDestino": "DOLARES",
       "monto": 23
     }
     ```
     - Response:
     
     ```javascript
     {
       "monto": 23,
       "montoConTipoCambio": 5.98,
       "monedaOrigen": "SOLES",
       "monedaDestino": "DOLARES",
       "tipoCambio": 0.26
     }
     ```
