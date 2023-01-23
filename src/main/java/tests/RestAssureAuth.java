package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import utils.DataBuilder;

public class RestAssureAuth {
	
	//https://restful-booker.herokuapp.com/apidoc/index.html#api-Auth-CreateToken
	
	//https://restful-booker.herokuapp.com/auth
	String token;
	int id;

	@Test (priority=1)
	public void getToken() {
		Response resp = given().
				contentType(ContentType.JSON).
				body(DataBuilder.buildToken().toJSONString()).
				post("https://restful-booker.herokuapp.com/auth").
				then().
				statusCode(200).
				extract().response();
		
		System.out.println(resp.asPrettyString());
		token=resp.jsonPath().getString("token");
	}
	
	@Test (priority=2)
	public void createBooking() {
		Response resp = given().
				contentType(ContentType.JSON).
				body(DataBuilder.buildBooking().toJSONString()).
				post("https://restful-booker.herokuapp.com/booking").
				then().
				statusCode(200).
				extract().response();
		
		System.out.println(resp.asPrettyString());
		id=resp.jsonPath().getInt("bookingid");
	}
	
	@Test(priority=3)
	public void deleteBooking() {
		//https://restful-booker.herokuapp.com/booking/1
		
		Response resp = given().
				header("Cookie","token="+token).
				contentType(ContentType.JSON).
				delete("https://restful-booker.herokuapp.com/booking/"+id).
				then().
				statusCode(201).
				extract().response();
		
		//sau varianta asta:
	/*	Response resp = given().
				auth().preemptive().basic("admin", "password123").
				delete("https://restful-booker.herokuapp.com/booking/"+id).
				then().
				statusCode(201).
	*/	
		System.out.println(resp.asPrettyString());
	
	}
	
}
