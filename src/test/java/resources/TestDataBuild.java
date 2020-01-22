package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);


		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);

		p.setName(name);
		p.setPhone_number("(+91) 758 884 6196");

		List<String> myList = new ArrayList<String>();
		myList.add("Shoe Park");
		myList.add("Shop");
		p.setType(myList);

		p.setWebsite("https://rahulshettyacademy.com");

		return p;
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n\"place_id\" : \""+placeId+"\"\r\n}";
	}

}
