package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;

public class Curs4_Tema {
	
	String id;
	
	@Test (priority=1)
	public void postATodo() {
	
		//Json Object creat direct-versiunea1
		JSONObject requestPayload =new JSONObject();
		//RestAssured.baseURI="https://keytrcrud.herokuapp.com/";
		
		//am pus in interiorul jsonului cele 2 campuri de care am nevoie
		Faker fake=new Faker();  //cu asta generam stringuri
		requestPayload.put("name", fake.name().lastName());
		requestPayload.put("email", fake.internet().emailAddress());
		requestPayload.put("age", fake.number().numberBetween(5, 100));
		requestPayload.put("gender", "f");

		System.out.println("--------------Start post request-------------");
		
		Response response=RestAssured
				.given()
					.header("Content-Type","application/json")
			
				.when()
				//fie se scrie direct aci jsonul (ca linia de sus), fie printr-un obiect json ca mai jos
				  .body(requestPayload.toJSONString())
			
					.post("https://keytrcrud.herokuapp.com/api/users")
				.then()
				  .assertThat().statusCode(201)
				  .extract().response();
		
		System.out.println(response.asPrettyString());
		
		id= response.jsonPath().getString("result._id"); //jsonPath permite sa intri in interiorul response
		System.out.println("Id-ul din post response este: " +id); 
		
		String success= response.jsonPath().getString("success"); //jsonPath permite sa intri in interiorul response
		System.out.println(success);
		assertEquals(success,"true");
		
		String msg= response.jsonPath().getString("msg"); //jsonPath permite sa intri in interiorul response
		System.out.println(msg);
		assertEquals(msg,"Successfully added!");
		
		assertEquals(response.statusCode(),201);
		
		System.out.println("--------------End post request-------------");
		
	}
	
	@Test(priority=2)
	public void getATodo() {
		System.out.println("--------------Start get request-------------");
		
		Response response=given().
			  	  header("Content-Type","application/json")
				.get("https://keytrcrud.herokuapp.com/api/users/"+id).
				then().
				statusCode(200).
				extract().response();
		
		System.out.println(response.jsonPath().getString("_id")); 
		System.out.println(response.asPrettyString());
		
		assertEquals(response.statusCode(),200);
		
		System.out.println("--------------End get request-------------");
	}
	
	@Test(priority=3)
	public void DeleteTodo() {
		
		System.out.println("--------------Start delete request-------------");
		
		Response response=given().
				      header("Content-Type","application/json").
				      delete("https://keytrcrud.herokuapp.com/api/users/"+id).
		             then().
		            statusCode(200).
		            extract().response();
		
		String success= response.jsonPath().getString("success"); //jsonPath permite sa intri in interiorul response
		System.out.println(success);
		assertEquals(success,"true");
		
		String msg= response.jsonPath().getString("msg"); //jsonPath permite sa intri in interiorul response
		System.out.println(msg);
		assertEquals(msg,"It has been deleted.");
		
		assertEquals(response.statusCode(),200);
		
		System.out.println("--------------End delete request-------------");
	}

	
}
