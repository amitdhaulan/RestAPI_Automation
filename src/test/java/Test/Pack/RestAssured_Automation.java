package Test.Pack;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssured_Automation {
    private static String requestBody = "{\n" +
            "  \"title\": \"foo\",\n" +
            "  \"body\": \"bar\",\n" +
            "  \"userId\": \"1\" \n}";

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

    @Test(priority = 1)
    public void getRequestWithQueryParameter() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .param("postId", "2")
                .when().log().all()
                .get("/comments")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Meghan_Littel@rene.us", response.jsonPath().getString("email[3]"));
    }

    @Test(priority = 2)
    public void postRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("foo", response.jsonPath().getString("title"));
        Assertions.assertEquals("bar", response.jsonPath().getString("body"));
        Assertions.assertEquals("1", response.jsonPath().getString("userId"));
        Assertions.assertEquals("101", response.jsonPath().getString("id"));
    }

    @Test(priority = 3)
    public void putRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("foo", response.jsonPath().getString("title"));
        Assertions.assertEquals("bar", response.jsonPath().getString("body"));
        Assertions.assertEquals("1", response.jsonPath().getString("userId"));
        Assertions.assertEquals("1", response.jsonPath().getString("id"));
    }

    @Test(priority = 4)
    public void patchRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{\n" +
                "  \"title\": \"bax\" \n}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .patch("/posts/1")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("bax", response.jsonPath().getString("title"));
        Assertions.assertEquals("1", response.jsonPath().getString("userId"));
        Assertions.assertEquals("1", response.jsonPath().getString("id"));
    }

    @Test(priority = 5)
    public void deleteRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/posts/1")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}
