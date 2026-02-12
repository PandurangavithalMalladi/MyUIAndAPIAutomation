package tests.API;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;

import org.testng.annotations.Test;

public class GetUserNotFoundSchemaTest {
    
    @Test
    public void validateUserNotFoundScenario() {

        baseURI = "https://jsonplaceholder.typicode.com";

        given()
        .when()
            .get("/users/9999")
        .then()
            .statusCode(404)
            .body("$", anEmptyMap());
    }

}
