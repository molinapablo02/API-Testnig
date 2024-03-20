Feature: Ejemplo de request

@API
Scenario: Prueba Get al endpoint
    Given The user send a GET request to the https://api.github.com URI
    Then The user get a 200 status code


Scenario: Validar la cantidad de entradas en la respuesta
    Given The user send a GET request to the https://jsonplaceholder.typicode.com URI
    Then The user validates there are 10 items on the /users endpoint


Scenario: Validar que un elemento esta en la respuesta
    Given The user send a GET request to the https://jsonplaceholder.typicode.com URI
    Then The user validates there is a value: Brent in the response at /users endpoint