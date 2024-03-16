package Steps;

import static io.restassured.RestAssured.*;
import java.util.Base64;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EjAuthentication {
    
    public void basicAuth(String username, String password){

        given()
        .auth().basic(username, password)
        .when()
        .get("AUTH_ENDPOINT")
        .then()
        .assertThat().statusCode(200);

    }

    public void formAuth(String username, String password){

        given()
        .auth().form(username, password)
        .when()
        .get("service")
        .then()
        .assertThat().statusCode(200);

    }

    /*
     * 1) Obtener el codigo del servicio original para obtener el token.
     * 2) Obtener el token, intercambiando el codigo que obtuvimos.
     * 3) Acceder al recurso protegido, con nuestro toker. 
     */

    static String clienteId = "";
    static String redirectUri = "";
    static String scope = "";
    static String username = "";
    static String password = "";
    static String grantType = "";

    //mandamos el username y password de manera segura
    public static String encode(String str1, String str2){
        return new String (Base64.getEncoder().encode((str1 + ":" + str2).getBytes()));
    }

    // ahora necesitamos el codigo
    public static Response getCode(){

    String authorization = encode(username, password);

    return 
            given()
            .header("authorization", "basic" + authorization)
            .contentType(ContentType.URLENC)
            .formParam("responese_type", "code")
            .queryParam("clientId", clienteId)
            .queryParam("redirect_uri", redirectUri)
            .queryParam("scope", scope)
            .post("/oauth o /authorize")
            .then()
            .statusCode(200)
            .extract()
            .response();

    }

    public static String parseForOauthCode(Response response){
        return response.jsonPath().getString("code");
    }

    public static Response getToken(String authCode){

        String authorization = encode(username, password);

        return 
            given()
            .header("authorization", "basic" + authorization)
            .contentType(ContentType.URLENC)
            .formParam("responese_type", authCode)
            .queryParam("redirect_uri", redirectUri)
            .queryParam("grant_type", grantType)
            .post("/oauth/token")
            .then()
            .statusCode(200)
            .extract()
            .response();

    }

    public static String parseForToken(Response loginResponse){
        return loginResponse.jsonPath().getString("access_token");
    }
}
