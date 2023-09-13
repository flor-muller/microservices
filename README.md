# parcialBackendI

Circuit Braker:
Se implementó el patrón circuit braker en el microservicio de Catálogo, por considerarlo el más utilizado. 
este patrón se aplicó específicamente en el método GET utilizado para consultar géneros.
De este modo, en un escenario en que se realiza una consulta de géneros que incluye series y películas, 
frente a un eventual error del servidor, el circuit braker entra en acción. El cliente, luego de una cantidad establecida de intentos fallidos, 
va a poder obtener una respuesta predefinida en el método fallback.
Se configuraron tanto la cantidad de errores que se toleran hasta que se abre el circuito como la cantidad de reintentos que se realizan 
automáticamente, de este modo se reducen las solicitudes de reintento necesarias por parte del cliente.
