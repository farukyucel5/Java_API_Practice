package SwaggerApiQuery;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest1 {

    @Test
    public void get01(){

        String url="https://petstore.swagger.io/v2/pet/findByStatus?status=sold";

        Response response = given().when().get(url);

        response.prettyPrint();


    }
    @Test
    public void get02(){
        String url2="https://petstore.swagger.io/v2/pet/21";

        Response response=given().when().get(url2);

        response.prettyPrint();

        System.out.println("Status Code : " +response.getStatusCode());
        System.out.println("Content Type : " +response.getContentType());
        System.out.println("Server Header'inin Degeri : " +response.getHeader("Server"));
        System.out.println("Status Line : " +response.getStatusLine());
        System.out.println("Response Suresi : " +response.getTime());

        response.
                then().
                assertThat().
                statusCode(200).
                header("Server","Jetty(9.2.9.v20150224)").
                statusLine("HTTP/1.1 200 OK");

    }
    @Test
    public void get03(){
        String url="https://petstore.swagger.io/v2/store/order/7";
        Response response=given().when().get(url);
        response.prettyPrint();

        response.
                then().assertThat().
                contentType(ContentType.JSON).
                statusCode(200);


    }
}
