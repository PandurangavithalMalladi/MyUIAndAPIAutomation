package tests.API;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

public class UserContractAgainstTest {

    @Test
    public void validateUserAgainstOpenApiSpec() {

    	baseURI = "https://jsonplaceholder.typicode.com";

    	given()
    	.when()
    	    .get("/users/1")
    	.then()
    	    .statusCode(200)
    	    .body(matchesJsonSchemaInClasspath("schema/user-schema.json"));

    }
}
