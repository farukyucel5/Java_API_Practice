package BaseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class SwaggerBaseUrl {

   protected RequestSpecification swaggerSpec;

   @Before
   public void setup(){

       swaggerSpec=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/").build();
   }

}
