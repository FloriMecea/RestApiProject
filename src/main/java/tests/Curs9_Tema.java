package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
//import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.DataBuilder;

public class Curs9_Tema {
	String token;
	String id_post;

	@Test (priority=1)
	public void post1() {
		Response resp = given().
				contentType(ContentType.JSON).
				body(DataBuilder.buildUser().toJSONString()).
				post("https://dev-todo.herokuapp.com/api/auth").
				then().
				statusCode(200).
				extract().response();
		
		System.out.println(resp.asPrettyString());
		token=resp.jsonPath().getString("token");
		id_post=resp.jsonPath().getString("id");
		
		System.out.println("tokenul este: "+token);
		System.out.println("id-ul este: "+id_post);
		assertThat(token, is(not(equalTo (null))));
		assertThat(id_post, is(not(equalTo (null))));
	}
	

	@Test (priority=2)
	public void post2() {
		Response resp = given().
				contentType(ContentType.JSON).
				body(DataBuilder.buildToDo().toJSONString()).
				post("https://dev-todo.herokuapp.com/api/auth/save").
				then().
				statusCode(401).
				extract().response();
		
		System.out.println(resp.asPrettyString());
	    String	msg=resp.jsonPath().getString("msg");
	
		System.out.println("mesajul este: "+msg);
		assertEquals(msg, "Sorry, you are not authorized");
	
	}
	
	@Test (priority=3)
	public void post3() {
		Response resp = given().
				contentType(ContentType.JSON).
				header("Token",token).
				body(DataBuilder.buildToDo().toJSONString()).
				post("https://dev-todo.herokuapp.com/api/auth/save").
				then().
				statusCode(200).
				extract().response();
		
		System.out.println(resp.asPrettyString());
		String id_post3=resp.jsonPath().getString("id");
	  	
		System.out.println("id-ul este: "+id_post3);
		assertThat(id_post3, is(not(equalTo (null))));
	
	}
	
	@Test(priority=4)
	public void deleteBooking1() {
		
		
		Response resp = given().
				contentType(ContentType.JSON).
				delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id_post).
				then().
				statusCode(401).
				extract().response();
		
		System.out.println(resp.asPrettyString());
	    String	msg=resp.jsonPath().getString("msg");
		
		System.out.println("mesajul este: "+msg);
		assertEquals(msg, "Sorry, you are not authorized");
	
	}
	
	@Test(priority=5)
	public void deleteBooking2() {
		
		String wrongToken="aaaa";
		Response resp = given().
				header("Token",wrongToken).
				contentType(ContentType.JSON).
				delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id_post).
				then().
				statusCode(503).
				extract().response();
		
		System.out.println(resp.asPrettyString());
	    String	msg=resp.jsonPath().getString("msg");
		
		System.out.println("mesajul este: "+msg);
		assertEquals(msg, "Wrong token");
	
	}
	
	@Test(priority=6)
public void deleteBooking3() {
		
		
		Response resp = given().
				header("Token",token).
				contentType(ContentType.JSON).
				delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id_post).
				then().
			//	statusCode(401).
				extract().response();
		
		System.out.println(resp.asPrettyString());
	    String	msg=resp.jsonPath().getString("msg");
		
		System.out.println("mesajul este: "+msg);
		assertEquals(msg, "Event deleted.");
	
	}
	
}
