import Code.GetRequestClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class RequestsTests {

    @Test()
    public void getRequestTestCase() {
        GetRequestClass getRequestClass = new GetRequestClass();
        Response response = getRequestClass.getRequest("https://catfact.ninja/fact"); // https://jsonplaceholder.typicode.com/posts
        /*List<String> title = response.jsonPath().getList("title");*/

        String title = response.jsonPath().getString("length"); // https://catfact.ninja/fact
        System.out.println(response.asString());
    }
}