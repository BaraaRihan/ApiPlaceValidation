Feature: Validating Place API's
  @AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload "<name>"  "<language>" "<address>"
    When user calls "AddPlaceApi" with "Post" http request
    Then the Api call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "getPlaceAPI"
       #GET METHOD


  Examples:
   | name     | language | address                 |
   |Bero House| French-IN|29, side layout, cohen 09|
  # |KOK House| Arabic   | Cairo, cross town       |

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "Delete" http request
    Then the Api call is success with status code 200
    And "status" in response body is "OK"