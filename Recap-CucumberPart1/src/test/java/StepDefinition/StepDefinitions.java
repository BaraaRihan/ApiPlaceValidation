package StepDefinition;

import Pojo.Payload;
import Pojo.location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {
    RequestSpecification Req;
    ResponseSpecification res;
    Response response;




    TestDataBuild Data =new TestDataBuild();
    //JsonPath j;

    static String place_id;  //lazm static 3shan all test cases kda will refer to the same variable


    Utils req = new Utils();
    //Utils res = new Utils();

    @Given("Add Place Payload {string}  {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
         Req = given().log().all().spec(req.requestSpecification()).body(Data.addPlacePayload(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource,String method) {

         APIResources recourseAPI=APIResources.valueOf(resource);

         if(method.equalsIgnoreCase("Post"))
             response = Req.when().post(/*"/maps/api/place/add/json"*/ recourseAPI.getResource());
         else if (method.equalsIgnoreCase("Get"))
             response = Req.when().get(/*"/maps/api/place/get/json"*/ recourseAPI.getResource());
         else if (method.equalsIgnoreCase("Delete"))
             response = Req.when().delete(recourseAPI.getResource());
         //kml b2a

        System.out.println(recourseAPI.getResource());




        /*  then().log().all().spec(res.ResponseSpecification()).extract().response();  */    //Malosh lazma 3shan ana 3amlto f method @Then nfs fekrto
    }
    @Then("the Api call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {

        assertEquals(response.getStatusCode(),200);


    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String KeyValue, String ExpectedValue) {

       /*String Scope = response.asString();
        JsonPath j = new JsonPath(Scope);
        String b = j.getString("scope");
        assertEquals(b,"APP");*/

        String Scope = response.asString();
         JsonPath j = new JsonPath(Scope);
        //System.out.println("SCOppyy : "+Scope);
        assertEquals(j.get(KeyValue),ExpectedValue); //gammmmmmmmmmmmed mout v103 Rahoul SHetty



    }
    @Then("Verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        //reqSpec


      /*  String place_id =j.getString("place_id");
        Req =  given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"Get");
        String actualname = j.getString("name");
        System.out.println(Name);
        System.out.println(actualname);*/

        place_id=getJsonPath(response,"place_id");
        Req=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"Get");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);

        System.out.println(actualName);
        System.out.println(expectedName);

    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {

       Req= given().spec(requestSpecification()).body(Data.deletePlacePayload(place_id));
    }
}
