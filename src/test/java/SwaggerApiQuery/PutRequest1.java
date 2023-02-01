package SwaggerApiQuery;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PutRequest1 {
    /*
    {
    "id": 91981061,
    "category": {
      "id": 0,
      "name": "animals"
    },
    "name": "Snop",
    "photoUrls": [
      "string"
    ],
    "tags": [
      {
        "id": 0,
        "name": "string"
      }
    ],
    "status": "sold"
  }
     */

   @Test
    public  void put1(){
       String url="https://petstore.swagger.io/v2/pet";
       JSONObject categoryBody=new JSONObject();
       categoryBody.put("id",14);
       categoryBody.put("name","dog");

       JSONObject[] tagBody=new JSONObject[1];
       JSONObject tagElement1=new JSONObject();
       tagElement1.put("id",23);
       tagElement1.put("name","Kangal");
       tagBody[0]=tagElement1;

      String[] photoUrl=new String[1];
      photoUrl[0]="https://m.facebook.com/dogfoto.hu/photos/a.406528193265713/752121942039668/";


       JSONObject reqBody = new JSONObject();
       reqBody.put("id",91981061);
       reqBody.put("name","Kılıç");
       reqBody.put("status","pending");
       reqBody.put("category",categoryBody);
       reqBody.put("tags",tagBody);
       reqBody.put("photoUrls",photoUrl);





       System.out.println(reqBody);

       // 2 - Soruda istendiyse Expected Data hazirla

       // 3 - Response'i kaydet

       Response response = given().
               contentType(ContentType.JSON).
               when().
               body(reqBody.toString()).
               put(url);
       response.prettyPrint();

       // 4 - Assertion

       response.
               then().
               assertThat().
               statusCode(200).
               body("id",equalTo(91981061),
                       "name",equalTo("Kılıç"));
   }
}
