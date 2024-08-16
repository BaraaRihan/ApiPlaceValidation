import Pojo.Payload;
import Pojo.location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlace {

    public static void main(String[] args) {


       RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
               .setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();

       ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();

       location l = new location();
       l.setLat(-38.383494);
       l.setLng(33.427362);


       Payload p = new Payload();
       p.setLocation(l);
       p.setAccuracy(50);
       p.setName("Frontline house");
       p.setPhone_number("(+91) 983 893 3937");
       p.setAddress("29, side layout, cohen 09");

       ArrayList<String> types = new ArrayList<String>();
       types.add("shoe park");
       types.add("shop");
       p.setTypes(types);


       RequestSpecification Req = given().log().all().spec(req).body(p);

       String Response = Req.when().post("/maps/api/place/add/json").then().spec(res).extract().response().asString();
       System.out.println(Response);


    }







}
