package utils;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class BaseComponent3 {
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		requestSpec=new RequestSpecBuilder()
	//			.setBaseUri("https://keytrcrud.herokuapp.com/"   -Base2Example
	//			.setBasePath("api/users/")
				.setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/")
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
	
	public static Response doRequest(String method, String id, String body) {

		Response result=null;
		
		
		switch (method.toUpperCase()){
		case "GET": result= given().spec(requestSpec).get(id);
		   break;
		case "POST": result= given().spec(requestSpec).body(body).post("save");  
		  break;
		case "PUT": result= given().spec(requestSpec).body(body).put("todos/"+id);  
		  break;
		case "DELETE": result= given().spec(requestSpec).delete("delete/"+id);  
		  break;
		
		}
		
		if(result!=null) {
			result=result.then().spec(responseSpec).extract().response();
		}
		  
		return result;
	}
	
	
}
