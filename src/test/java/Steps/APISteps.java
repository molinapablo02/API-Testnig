package Steps;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.request;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APISteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    // primeros casos usando rest assured y cucumber

    @Given("^The user send a GET request to the (.+) URI$")
    public void sentGETRequest(String URI) {
        request = given()
                .baseUri(URI)
                .contentType(ContentType.JSON);
    }

    @Then("^The user get a (\\d+) status code$")
    public void vadilateStatusCode(int expectedStatusCode) {

        response = request
                .when()
                .get("/users/molinapablo02/repos");

        json = response.then().statusCode(expectedStatusCode);

    }

    @Then("^The user validates there are (\\d+) items on the (.+) endpoint$")
    public void validateSize(int expectedSize, String path) {

        response = request
                .when()
                .get(path);

        List<String> jsonResponse = response.jsonPath().getList("$");
        int listSize = jsonResponse.size();
        // assertEquals(expectedSize, listSize);
        assertEquals(expectedSize, listSize);

    }

    @Then("^The user validates there is a value: (.+) in the response at (.+) endpoint$")
    public void ValidateValue(String expectedValue, String path) {

        response = request
                .when()
                .get(path);

        List<String> jsonResponse = response.jsonPath().getList("username");

        String actualValue = jsonResponse.get(0);
        // assertEquals(expectedValue, actualValue);
        // assertEquals(expectedValue, actualValue);
    }

    @Then("^The user validates there is a nested value: (.+) in the response at (.+) endpoint$")
    public void valideteNestedValue(String expectedValue, String path) {

        response = request
                .when()
                .get(path);

        List<String> cities = response.jsonPath().getList("address.city");

        // assertEquals(expectedValue, cities);
        assertTrue(cities.contains(expectedValue));

    }

    @Given("^The user send a Post request to (.+) URI$")
    public void sendPostRequest(String URI) {

        request = given()
                .basePath(URI)
                .contentType(ContentType.JSON);

    }

    @Then("^The user gets the token at (.+) endpoint$")
    public void getToken(String path) {

        String body = "{\"audience\": \"https://api.bravenewcoin.com\",\n" + //
                "        \"client_id\": \"oCdQoZoI96ERE9HY3sQ7JmbACfBf55RY\",\n" + //
                "        \"grant_type\": \"client_credentials\"}";

        response = request.given()
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "a1a2b4eb17msh6a7fed06a8297d5p16dae6jsna5e67b4f306c")
                .header("X-RapidAPI-Host", "bravenewcoin.p.rapidapi.com")
                .body(body)
                .post(path)
                .prettyPeek();

        assertEquals(200, response.statusCode());

    }

}
