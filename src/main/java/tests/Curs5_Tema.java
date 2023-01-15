package tests;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.BaseComponent_Tema_Curs5;

public class Curs5_Tema extends BaseComponent_Tema_Curs5{
    String id, name1;
 
	
	@Test(priority = 1)
	public void postUser() {
		
		System.out.println("-----------------------Start Post request---------------------");
		
		JSONObject body =  new JSONObject();
	
	    Faker faker = new Faker();
		String name= faker.name().firstName();
		
		System.out.println("numele generat este: " +name);
		
		body.put("name", name);
		body.put("trips", 250);
		body.put("airline",1981);
				
		Response respose = doPostRequest("v1/passenger/", body.toJSONString(), HttpStatus.SC_OK);
		assertEquals(respose.jsonPath().getString("name"), name);
		System.out.println("numele din response este: " +respose.jsonPath().getString("name"));
        System.out.println(respose.asPrettyString());
		id = respose.jsonPath().getString("_id");
		System.out.println("id-ul pasagerului este: " +id);
		
		name1 = respose.jsonPath().getString("name");
		System.out.println("numele pasagerului din response este: " +name1);
		
		System.out.println("-----------------------End Post request---------------------");
	}
	
	@Test(priority = 2)
	public void getUser() {
		System.out.println("-----------------------Start Get request---------------------");
		
		Response response = doGetRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().getString("airline[0].name"), "Tarom");
		
		System.out.println("-----------------------End Get request---------------------");
	}
	
	
	@Test(priority = 3)
    public void putUser() {
		
		System.out.println("-----------------------Start Put request---------------------");
		
		JSONObject body1 =  new JSONObject();

		System.out.println("numele pasagerului de va fi updatat este: " +name1);
		
		body1.put("name",name1);
		body1.put("trips", 300);
		body1.put("airline",1);
		System.out.println("Noul body de va fi trimis pt update este: " +body1);
				
		Response response = doPutRequest("v1/passenger/", body1.toJSONString(), HttpStatus.SC_OK, id);
		
        System.out.println(response.asPrettyString());
	
		assertEquals(response.jsonPath().getString("message"), "Passenger data put successfully completed.");

		
		System.out.println("-----------------------End Put request---------------------");
	}
	
	@Test(priority = 4)
	public void getUpdatedUser() {
		System.out.println("-----------------------Start Get request for updated user---------------------");
		
		Response response = doGetRequest("v1/passenger/", id, 200);
		System.out.println("response body este: " +response.asPrettyString());
		System.out.println(response.jsonPath().getString("airline[0].name"));
		assertEquals(response.jsonPath().getString("airline[0].name"), "Quatar Airways");
		System.out.println(response.jsonPath().getString("trips"));
		assertEquals(response.jsonPath().getString("trips"), "300");
		System.out.println(response.jsonPath().getString("name"));
		assertEquals(response.jsonPath().getString("name"), name1);
		
		System.out.println("-----------------------End Get request for updated user---------------------");
	}
	
	@Test(priority = 5)
	public void deleteUser() {
		
		System.out.println("-----------------------Start Delete request for a user---------------------"); 
		System.out.println(" id-ul userului ce va fi sters este: " +id); 
		Response response = doDeleteRequest("v1/passenger/", id, 200);
		System.out.println("response body este: " +response.asPrettyString());
			
		assertEquals(response.jsonPath().getString("message"), "Passenger data deleted successfully.");
		
		System.out.println("-----------------------End Delete request for a user---------------------"); 
	}
	
}