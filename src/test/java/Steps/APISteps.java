package Steps;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
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

    //primeros casos usando rest assured y cucumber

    @Given("^The user send a GET request to the (.+) URI$")
    public void sentGETRequest(String URI){
        request = given()
                    .baseUri(URI)
                    .contentType(ContentType.JSON);
    }

    @Then("^The user get a (\\d+) status code$")
    public void vadilateStatusCode(int expectedStatusCode){

        response = request
                    .when()
                    .get("/users/molinapablo02/repos");

                    json = response.then().statusCode(expectedStatusCode);
                     
    }

    @Then("^The user validates there are (\\d+) items on the (.+) endpoint$")
    public void validateSize(int expectedSize, String path){

        response = request
                    .when()
                    .get(path);
                    
        List<String> jsonResponse = response.jsonPath().getList("$");
        int listSize = jsonResponse.size();
        //assertEquals(expectedSize, listSize);
        assertEquals(expectedSize, listSize);
        
    }
 
    @Then("^The user validates there is a value: (.+) in the response at (.+) endpoint$")
    public void ValidateValue(String expectedValue, String path){

        response = request
                    .when()
                    .get(path);

        List<String> jsonResponse = response.jsonPath().getList("$");

        String actualValue = jsonResponse.get(0);
        assertEquals(expectedValue, actualValue);
        

    }
}
