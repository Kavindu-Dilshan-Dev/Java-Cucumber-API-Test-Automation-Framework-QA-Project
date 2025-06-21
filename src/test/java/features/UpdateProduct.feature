
 Feature: insert products using PUT API

   Scenario Outline: validate put product api status code works correctly
     Given I hit the url of the put product api endpoint
     When I pass the url of the products in the request with <ProductNumber>
     And I pass the request body of put api
     Then I receive the response code as 200
    Examples:
     | ProductTitle |
     | Shoes        |
