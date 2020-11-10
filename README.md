# Test Awto TI
 
Microservicio para el control y registro de logs


### TECNOLOGÍA

  - Java 8, Spring boot + maven.
  - JPA.
  - MySQL.



### ENDPOINTS

- POST /logs 
   > Cuerpo de la petición: [https://pastebin.com/HzvbZhjk](https://pastebin.com/GpA4rzZg)
- GET /logs: 
   > Obtiene todos los logs
   > Cuerpo de la respuesta: [https://pastebin.com/GpA4rzZg](https://pastebin.com/GpA4rzZg)
- GET /logs/{hashtag}:
  > Obtiene todos los logs según su hashtag.
  > Cuerpo de la respuesta: el mismo que el GET de arriba.
- GET /log/{logId}: 
  > Obtiene el log según su id.
  > Cuerpo de la respuesta: [https://pastebin.com/q7dt2Fj3](https://pastebin.com/q7dt2Fj3)
- PUT /hastags: 
  > Cambia el nombre de un hashtag en particular a través de su id.
  > Cuerpo de la petición: [https://pastebin.com/p8wYc0FJ](https://pastebin.com/p8wYc0FJ)

### Entidades


**awlog_logger**
- id (INTEGER AUTO_INCREMENT)
- creation_date (TIMESTAMP)
- host (VARCHAR(100))
- origin (VARCHAR(100))
- details (TEXT)
- stacktrace (TEXT)

**awlog_hashtag**
- id (INTEGER AUTO_INCREMENT) ◦ description (VARCHAR(50))

**awlog_logger_hashtag**
- id (INTEGER AUTO_INCREMENT) ◦ log_id INTEGER
- hastag_id INTEGER
