package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BaseComponent_Tema_Curs6_Part2 {

	public static RequestSpecification requestSpec, requestSpec1;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		requestSpec=new RequestSpecBuilder()
	//			.setBaseUri("https://keytrcrud.herokuapp.com/"   -Base2Example
	//			.setBasePath("api/users/")
				.setBaseUri("https://keytodorestapi.herokuapp.com/api/")
				.setBasePath("api/save")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
	
	
	requestSpec1=new RequestSpecBuilder()
//			.setBaseUri("https://keytrcrud.herokuapp.com/"   -Base2Example
//			.setBasePath("api/users/")
			.setBaseUri("https://keytodorestapi.herokuapp.com/api/")
			.setBasePath("api")
			.setContentType(ContentType.JSON)
			.addHeader("accept", "application/json")
			.build();
   }
	
	@BeforeClass
	public static void createResponseSpecification() {
		responseSpec =  new ResponseSpecBuilder()
				.expectStatusCode(anyOf(is(200),is(201)))
				.build();
		
	}
	
	
	public static Response doPostRequest(String todo) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(todo).
				when().
				    post().
				then().
				    spec(responseSpec).
					extract().response();
			
		return result;
		
	}
	
public static Response doPutRequest(String body, String id) {
		
		Response result = 
				given().
				    spec(requestSpec).
					body(body).
				when().
					put(id).
				then().
				 spec(responseSpec).
					extract().response();
			
		return result;
		
	}

public static Response doGetOneRequest(String id) {
	
	Response result = 
			given().
		       spec(requestSpec).
			when().
				get(id).
			then().
			    spec(responseSpec).
				extract().response();
		
	return result;
}
public static Response doGetAllRequest() {
	
	Response result = 
			given().
		       spec(requestSpec1).
			when().
				get().
			then().
			    spec(responseSpec).
				extract().response();
		
	return result;
}


public static Response doDeleteRequest(String id) {
	
	Response result = 
			given().
		      spec(requestSpec).
			when().
				delete(id).
			then().
			    spec(responseSpec).
				extract().response();
		
	return result;
}
	
}
