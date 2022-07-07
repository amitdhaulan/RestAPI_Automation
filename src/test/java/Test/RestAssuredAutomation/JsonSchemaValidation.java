package Test.RestAssuredAutomation;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidation {

    @Test
    public void testGet() {
        RestAssured.baseURI = "https://reqres.in/api";
        given()
                .get("/products").then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"))
                .statusCode(200);
    }
}