package SwaggerApiQuery;

import BaseUrls.SwaggerBaseUrl;
import Pojos.PetExpectedBody;
import Pojos.Pet_Category;
import Pojos.TagsInnerBody;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Pet_Post_Request extends SwaggerBaseUrl {


    /*
        {
      "id": 12,
      "category": {
        "id": 445,
        "name": "sword-tring"
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
    https://petstore.swagger.io/v2/pet
         */
    @Test
    public void swaggerPost(){
        swaggerSpec.pathParam("pp1","pet");
        String[] photoUrl={"https://tr.pinterest.com/pin/679480662510500791/"};
        Pet_Category petCategory=new Pet_Category(57,"Dog");

        TagsInnerBody tagsInnerBody=new TagsInnerBody(23,"kangal");

        TagsInnerBody[] tagsInnerBodies={tagsInnerBody};

        PetExpectedBody petRequestBody=new PetExpectedBody(554,petCategory,"Kılıc",photoUrl,tagsInnerBodies,"available");
        PetExpectedBody petExpectedBody=new PetExpectedBody(554,petCategory,"Kılıc",photoUrl,tagsInnerBodies,"available");
        Response response=given().spec(swaggerSpec).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).when().body(petRequestBody).post("{pp1}");


        assertEquals(petExpectedBody.getId(),petRequestBody.getId());
        assertEquals(petExpectedBody.getCategory().getId(),petRequestBody.getCategory().getId());
        assertEquals(petExpectedBody.getCategory().getName(),petRequestBody.getCategory().getName());
        assertEquals(petExpectedBody.getName(),petRequestBody.getName());
        assertEquals(petExpectedBody.getPhotoUrls()[0],petRequestBody.getPhotoUrls()[0]);
        assertEquals(petExpectedBody.getTags()[0].getId(),petRequestBody.getTags()[0].getId());
        assertEquals(petExpectedBody.getTags()[0].getName(),petRequestBody.getTags()[0].getName());
        assertEquals(petExpectedBody.getStatus(),petRequestBody.getStatus());



    }



}
