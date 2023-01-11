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
  String id;
	
	@Test(priority = 1)
	public void postUser() {
		
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
	}
	
	@Test(priority = 2)
	public void getUser() {
		
		Response response = doGetRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().getString("airline[0].name"), "Tarom");
	}
//	@Test(priority = 3)
	public void deleteUser() {
		
		Response response = doDeleteRequest("api/users/", id, 200);
		assertEquals(response.jsonPath().get("result._id"), id);
		assertEquals(response.jsonPath().getString("msg"), "It has been deleted.");
	}
	
}
