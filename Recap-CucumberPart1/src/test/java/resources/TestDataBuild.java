package resources;

import Pojo.Payload;
import Pojo.location;

import java.util.ArrayList;

public class TestDataBuild {

    public Payload addPlacePayload(String name,String language,String address)
    {
        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        Payload p = new Payload();
        p.setLocation(l);
        p.setAccuracy(50);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setLanguage(language);
        p.setAddress(address);
        ArrayList<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("shop");
        p.setTypes(types);
        p.setWebsite("http://google.com");
        return p;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n    \"place_id\":\""+ placeId +"\"\r\n}\r\n";
    }
}
