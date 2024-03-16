package Steps;

import static io.restassured.RestAssured.given;

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

    @Given("^The user sent a GET request to the endpoint$")
    public void sentGETRequest(){
        request = given()
                    .baseUri("https://api.github.com")
                    .contentType(ContentType.JSON);
    }

    @Then("^The user get a list of (//d+) users$")
    public void vadilateListOfUser(int expectedStatusCode){

        response = request.
                    when()
                    .get("/users/pablomolina02/repos");

                    json = response.then().statusCode(expectedStatusCode);
                    
        
                    //GENERAR EL REPORTE Y VER POR QUE FALLA EL TEST
    }
}
