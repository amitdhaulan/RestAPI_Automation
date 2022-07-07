package Test.RestAssuredAutomation;

import Code.GetRequestClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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

    public static class RequestsTests {

        @Test()
        public void getRequestTestCase() {
            GetRequestClass getRequestClass = new GetRequestClass();
            Response response = getRequestClass.getRequest("https://catfact.ninja/fact"); // https://jsonplaceholder.typicode.com/posts
            /*List<String> title = response.jsonPath().getList("title");*/

            String title = response.jsonPath().getString("length"); // https://catfact.ninja/fact
            System.out.println(response.asString());
        }
    }
}