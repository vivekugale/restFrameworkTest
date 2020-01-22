Feature: validating place api's

@AddPlace
Scenario Outline: verify if place is being succussfully added using addPlaceAPI

	Given Add Place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "post" http request
	Then then API call is success with status code 200 
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
	Examples:
		|name	|language|address			|
		|AaHouse|English |World Cross Center|
		#|BbHouse|Spanish |7 CrossRoad vila  |
	
@DeletePlace		
Scenario: Verify if delete place functionality is working

	Given Delete place payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then then API call is success with status code 200 
	And "status" in response body is "OK"
	

		
		