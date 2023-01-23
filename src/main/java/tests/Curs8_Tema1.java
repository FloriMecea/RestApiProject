package tests;

import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Curs8_Tema1 {
	
	//https://swapi.dev/api/people/4/

	@Test (priority=1)
	public void schemaValidation() {
		Response resp=given().
				get("https://swapi.dev/api/people/4/").
				then().
				log().all()
				.statusCode(200).extract().response();
		
		System.out.println(resp.asString());
		assertThat(resp.asString(),matchesJsonSchemaInClasspath("data_curs8_tema1_correctSchema.json"));
	   
	}
	
	@Test (priority=2)
	public void WrongSchemaValidation() {
		Response resp=given().
				get("https://swapi.dev/api/people/4/").
				then().
				log().all()
				.statusCode(200).extract().response();
		
		System.out.println(resp.asString());
		assertThat(resp.asString(),matchesJsonSchemaInClasspath("data_curs8_tema1_WrongSchema.json"));
	   
	}
 
	  
	
}
