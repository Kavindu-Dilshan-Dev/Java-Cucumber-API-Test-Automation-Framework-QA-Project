package stepdefintions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static  org.junit.Assert.*;

public class Products {

    public int statusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    JSONObject requestParams;


    @Given("I hit the url of the get products api endpoint")
    public void I_hit_the_url_of_the_get_products_api_endpoint(){
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of the products in the request")
    public void I_pass_the_url_of_the_products_in_the_request(){

    }

    @Then("I receive the response code as {int}")
    public void I_receive_the_response_code_as_(){
        responseCode = response.getStatusCode();
        assertEquals(responseCode , 200);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String rate){
        body = response.getBody();

        // by the following codee it converts response body to string
        String responseBody = body.asString();

        // by the following codee it converts response body to json
        JsonPath jsonPath = response.jsonPath();

        String s = jsonPath.getJsonObject("rating[0].rate").toString();

        assertEquals(rate , s);
    }


    @Given("I hit the url of the get products api endpoint")
    public void i_hit_the_url_of_the_post_product_api_endpoint(){

        RestAssured.baseURI = "https://fakestoreapi.com/";

        httpRequest = RestAssured.given();

        requestParams = new JSONObject();



    }

    @And("I pass the request body of product title {}")
    public void i_pass_the_request_body_of_product_title(String title){
        requestParams.put("title",title);
        requestParams.put("price",13.5);
        requestParams.put("description","best product");
        requestParams.put("category","sub-product");
        requestParams.put("image","http://example.com");
        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.put("/products");
        ResponseBody body = response.getBody();

        JsonPath jsnPath = response.jsonPath();
        String s = jsnPath.getJsonObject("id").toString();

        assertEquals("1",title);


        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void i_receive_the_response_body_with_id_as(String id){

    }

    @Given("I hit the url of the put product api endpoint")
    public void i_hit_the_url_of_the_put_product_api_endpoint(){
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of the products in the request with {}")
    public void i_pass_the_url_of_the_products_in_the_request_with(String productNumber){

        httpRequest = RestAssured.given();
        response = httpRequest.get("products"+ productNumber);

        requestParams.put("",productNumber);
        requestParams.put("price",13.5);
        requestParams.put("description","best product");
        requestParams.put("category","sub-product");
        requestParams.put("image","http://example.com");

    }

    @And("I pass the request body of put api")
    public void i_pass_the_request_body_of_put_api(){

    }

}
