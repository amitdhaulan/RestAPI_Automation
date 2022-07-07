package Code;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetRequestClass {

    public Response getRequest(String  endPoint){
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endPoint).
                        then().contentType(ContentType.JSON).extract().response();

        /*RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));*/

        /*//base URI with Rest Assured class
        RestAssured.baseURI = "https://run.mocky.io/v3";

        //input details
        RequestSpecification h = RestAssured.given();

        //get response
        Response res = h.get("/a1b7b64c-0204-409a-aa0c-e8314a5ddabf");

        //Response body
        ResponseBody b = res.getBody();*/

    }
}
