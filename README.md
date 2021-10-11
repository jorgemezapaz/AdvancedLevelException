# Advanced Exception in Spring boot
Manejo de excepciones avanzado con spring boot utilizando @RestControllerAdviced

Explicación ->   [Video youtube](https://youtu.be/GfIL78RxEx8).

Documentación -> [Error Handling for REST with Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)


``` 
curl -X POST "https://localhost:44332/personas" -H "accept: /" -H "Content-Type: application/json" -d "{\"id\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"run\":\"19305272-k\",\"nombres\":\"Francisco Antonio\",\"apellidoPaterno\":\"Gajardo\",\"apellidoMaterno\":\"Perez\",\"email\":\"francisco.gajardo@gmai.com\",\"sexoCodigo\":1,\"fechaNacimiento\":\"2021-10-10T23:51:15.021Z\",\"regionCodigo\":1,\"ciudadCodigo\":1,\"comunaCodigo\":1,\"direccion\":\"Los aperos\",\"telefono\":979318137,\"observaciones\":\"sin observaciones\"}"
```
