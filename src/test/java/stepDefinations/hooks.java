package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		stepDefinations m = new stepDefinations();

		if(stepDefinations.place_id==null) {
			m.add_Place_payload_with("Ugale", "French", "India");
			m.user_calls_with_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Ugale", "getPlaceAPI");
		}
	}

}
