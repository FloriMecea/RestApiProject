package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BaseComponent_Tema_Curs6_Part1 {
	
	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/";	

	}
	
	
	public static Response doPostRequest(String path, String body, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
					body(body).
				when().
					post(path).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
		
	}
public static Response doPutRequest(String path, String body, int statusCode, String id) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
					body(body).
				when().
					put(path+id).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
		
	}
	
	public static Response doGetRequest(String path, String id, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
				when().
					get(path + id).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
	}
	
	public static Response doGetAllRequest(String path, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
				when().
					get(path).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
	}
	
	public static Response doDeleteRequest(String path, String id, int statusCode) {
		
		Response result = 
				given().
					contentType(ContentType.JSON).
				when().
					delete(path + id).
				then().
					statusCode(statusCode).
					extract().response();
			
		return result;
	}

}
