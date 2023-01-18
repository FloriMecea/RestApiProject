package tests;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.BaseComponent_Tema_Curs6;
import utils.BaseComponent_Tema_Curs6_Part1;
import utils.BaseComponent_Tema_Curs6_Part2;
import utils.DataBuilder_Curs6_Tema2;

public class Curs6_Tema2_Versiunea2 extends BaseComponent_Tema_Curs6 {

	// URL: https://keytrcrud.herokuapp.com/ 

	public String id;
	public String info;
	
	@Test (priority=1)
	public void postUser() {
		//Response result=doPostRequest(DataBuilder_Curs6_Tema2.buildUser().toJSONString());  //dc extindeam BaseComponent2
		
		Response result=doPostRequest("api/save",DataBuilder_Curs6_Tema2.buildUser().toJSONString(),200);
		System.out.println(result.asPrettyString());
		System.out.println(result.asString());
		
		info=result.jsonPath().getString("info");
		System.out.println(info);
		id=result.jsonPath().getString("id");
		System.out.println(id);
		
		assertEquals(info,"Todo saved! Nice job!");
	}
	
	@Test(priority=2)
	public void getUser() {
		System.out.println("-------------------GET-------------------");
		//Response result=doGetOneRequest(id);
	//	assertEquals(email, result.jsonPath().getString("results.email"));
		
		File jsonFile = new File("todo.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		
		String title=jsonPath.getString("title");
		System.out.println(title);
		
	//	Response result=BaseComponent_Tema_Curs6_Part2.doGetAllRequest();
		
		Response result1=BaseComponent_Tema_Curs6.doGetAllRequest("api",200);
		
		System.out.println(result1.asString());
		System.out.println(result1.asPrettyString());
	//	System.out.println(jsonPath.getString("size()"));
		
	//  String id_get= result.jsonPath().getString("findAll{it.title == 'title'}.id");
		
	//	assertTrue(result.asString().contains(title));
		
	//	System.out.println("-------------------END GET-------------------");
	}
	
}
