package Test.RestAssuredAutomation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestFile {
    @Test(priority = 0)
    public void getRequestWithoutQueryParameter() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));
    }
}
