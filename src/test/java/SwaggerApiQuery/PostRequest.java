package SwaggerApiQuery;

import BaseUrls.SwaggerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

public class PostRequest extends SwaggerBaseUrl {
    /*
    {
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}
     */

    @Test
    public  void test01(){

        String endpoint="https://petstore.swagger.io/v2/pet";


        JSONObject categoryBody=new JSONObject();
        categoryBody.put("id",19);
        categoryBody.put("name","cow");

        JSONObject[] tagBody=new JSONObject[1];
        JSONObject tagElement1=new JSONObject();
        tagElement1.put("id",21);
        tagElement1.put("name","simmental");
        tagBody[0]=tagElement1;

        String[] photoUrl=new String[1];
        photoUrl[0]="https://en.wikipedia.org/wiki/Simmental_cattle";


        JSONObject reqBody = new JSONObject();
        reqBody.put("id",55);
        reqBody.put("name","DragonEyes");
        reqBody.put("status","pending");
        reqBody.put("category",categoryBody);
        reqBody.put("tags",tagBody);
        reqBody.put("photoUrls",photoUrl);

        Response response=given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().body(reqBody.toString())
                .post(endpoint);
        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                body("name",equalTo("DragonEyes"),
                        "id",lessThan(100),"status",
                        equalTo("pending"),"category.id",equalTo(19));



    }
    @Test
    public void checkPostRequest(){
        String endpoint="https://petstore.swagger.io/v2/pet/55";

        Response response=given().accept(ContentType.JSON).when().get(endpoint);

        response.prettyPrint();
        response.then().assertThat().body("photoUrls[0]",containsString("Simmental_cattle"));

    }
    @Test
    public void getByStatus(){
        String endPoint="https://petstore.swagger.io/v2/pet/findByStatus?status=sold";

        Response response=given().accept(ContentType.JSON).when().get(endPoint);

        response.then().assertThat().body("id",hasSize(3));



    }

    @Test
    public void getByStatus1(){
        swaggerSpec.pathParams("pp1","pet","pp2","findByStatus").
                queryParam("status","pending");

        Response response=given().spec(swaggerSpec).accept(ContentType.JSON).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        response.then().assertThat().body("category.id",hasItem(19),
                "tags[0].id",hasSize(1),"tags[0].id",hasItem(21));




    }
}
