package tests.API;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

public class GetUserListSchemaTest {
    
    @Test
    public void validateUsersListSchema() {

        baseURI = "https://jsonplaceholder.typicode.com";

        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath(
                "schema/users-list-schema.json"
            ));
    }
}
