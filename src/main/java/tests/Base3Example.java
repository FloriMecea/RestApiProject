package tests;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseComponent3;
import utils.DataBuilder;

public class Base3Example extends BaseComponent3 {
	
	/*
	 * GET1: https://keytodorestapi.herokuapp.com//api/{id}
	 * GET All: https://keytodorestapi.herokuapp.com/api
	 * POST: https://keytodorestapi.herokuapp.com/api/save
	 * PUT: https://keytodorestapi.herokuapp.com/api/todos/{id}
	 * DELETE: https://keytodorestapi.herokuapp.com/api/delete/{id}
	 * 
	 *  		requestSpec=new RequestSpecBuilder()
				.setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
	}
	 * 
	 */
	
	String id;

	@Test   //cele fara prioritate sunt primele executate in ordine alfabetica
	public void createTodo() {
		System.out.println(DataBuilder.buildToDo().toJSONString());
		Response resp=doRequest("POST", "", DataBuilder.buildToDo().toJSONString());
		
		System.out.println(resp.asPrettyString());
         id=resp.jsonPath().getString("id");
		
	}
	
	@Test (priority=1)
	public void getTodo() {	
		System.out.println("------------GET---------------------");
		Response resp=doRequest("GET", id,"");	
		System.out.println(resp.asPrettyString());
	
	}
	@Test (priority=2)
	public void updateTodo() {		
		System.out.println("------------UPDATE---------------------");
		System.out.println(DataBuilder.buildToDo().toJSONString());
		Response resp=doRequest("PUT", id, DataBuilder.buildToDo().toJSONString());
		
		System.out.println(resp.asPrettyString());
	
	}
	
	@Test (priority=3)
	public void deleteTodo() {		
		System.out.println("------------DELETE---------------------");
		System.out.println(DataBuilder.buildToDo().toJSONString());
		Response resp=doRequest("delete", id, DataBuilder.buildToDo().toJSONString());
		
		System.out.println(resp.asPrettyString());
	
	}
	
}
