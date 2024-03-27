Feature: Ejemplo de request


    Scenario: Prueba Get al endpoint
        Given The user send a GET request to the https://api.github.com URI
        Then The user get a 200 status code


    Scenario: Validar la cantidad de entradas en la respuesta
        Given The user send a GET request to the https://jsonplaceholder.typicode.com URI
        Then The user validates there are 10 items on the /users endpoint


    Scenario: Validar que un elemento esta en la respuesta
        Given The user send a GET request to the https://jsonplaceholder.typicode.com URI
        Then The user validates there is a value: Bret in the response at /users endpoint


    Scenario: Validar un elemento anidado
        Given The user send a GET request to the https://jsonplaceholder.typicode.com URI
        Then The user validates there is a nested value: Bartholomebury in the response at /users endpoint

    @API
    Scenario: Obtener Token
        Given The user send a Post request to https://bravenewcoin.p.rapidapi.com URI
        Then  The user gets the token at /oauth/token endpoint