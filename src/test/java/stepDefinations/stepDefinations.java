package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinations extends Utils {

	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	/*
	 * @Given("Add Place payload with {String} {String} {String}") public void
	 * add_Place_payload_with(String name, String language, String address) throws
	 * IOException {
	 * 
	 * res = given().spec(requestSpecification()).body(data.addPlacePayload(name,
	 * language, address)); }
	 */

	@Given("Add Place payload with {string} {string} {string}")
	public void add_Place_payload_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification())
				.body(data.addPlacePayload(name,language, address)); 
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {


		//constructor will be called with the value of resource which you pass 
		APIResources  resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
	}

	@Then("then API call is success with status code {int}")
	public void then_API_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedKeyValue) {
		assertEquals(getJsonPath(response,keyValue),expectedKeyValue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		//request specification
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");
		String Actualname = getJsonPath(response, "name");
		assertEquals(Actualname, expectedName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}

}
