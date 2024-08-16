package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {


    public static /* public static 3shan elPARAMETERIZE - dynamic data bardo elle f el example b2olo 5leeh yshofo 3sla tool */RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException
    {
        if (req==null) { //if is for dynamic data elle 7attha f el example b2olo 7otlle el data kolha f mkan wa7d
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
             req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .setContentType(ContentType.JSON ).addQueryParam("key", "qaclick123").
                    addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
            return req;
        }
        return req;
    }

    public  String getGlobalValue(String Key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("E:\\Automation\\DemoProject\\Recap-CucumberPart1\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(Key);

    }


    public String getJsonPath(Response response, String key)
    {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public ResponseSpecification ResponseSpecification()
    {
       ResponseSpecification res = new ResponseSpecBuilder()./*expectStatusCode(200).*/build();
        return res;
    }
}
