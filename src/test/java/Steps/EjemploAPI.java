package Steps;

import static io.restassured.RestAssured.given;

import org.junit.Test;

public class EjemploAPI {
    
    public void GETrequest(){
        given()
        .baseUri("https://api.github.com")
        .when()
        .get("users/pablomolina02/repos");
    }

    public void POSTrequest(){
        given()
        .baseUri("https://blogEjemplo.com")
        .post("/posts","Titulo: Texto");       
    }

    public void PUTrequest(){
        given()
        .baseUri("baseUri")
        .when()
        .put("","");      
    }

    public void DELETErequest(){
        given()
        .baseUri("https://api.blogEjemplo.com/posts/Texto")
        .when()
        .delete();
    }
    
}