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
import utils.BaseComponent_Tema_Curs6;
import utils.DataBuilder_Curs6_Tema2;

public class Curs6_Tema2 extends BaseComponent_Tema_Curs6 {

	public String id;
	public String info;
	public String id_get;
	
	@Test (priority=1)
	public void postUser() {
		System.out.println("-------------------POST-------------------");
			
		Response result=doPostRequest("api/save",DataBuilder_Curs6_Tema2.buildUser().toJSONString(),200);
		System.out.println(result.asPrettyString());
		System.out.println(result.asString());
		
		info=result.jsonPath().getString("info");
		System.out.println(info);
		id=result.jsonPath().getString("id");
		System.out.println(id);
		
		assertEquals(info,"Todo saved! Nice job!");
		System.out.println("-------------------End POST-------------------");
	}
	
	@Test(priority=2)
	public void getUserByTitle() {
		System.out.println("-------------------GET-------------------");
		
		File jsonFile = new File("todo.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		
		String title=jsonPath.getString("title");
		System.out.println(title);
		
		Response result1=BaseComponent_Tema_Curs6.doGetAllRequest("api",200);
		

		
	  id_get= result1.jsonPath().getString("find{it.title == '"+title+"'}._id");
	  System.out.println(id_get);
		
	  assertEquals(id,id_get);
		
	  System.out.println("-------------------END GET-------------------");
	}
	
	@Test(priority=3)
	public void deleteUser() {
		System.out.println("-------------------DELETE-------------------");
		 System.out.println(id_get);	
		
		Response result=BaseComponent_Tema_Curs6.doDeleteRequest("api/delete/",id_get,200);
		info=result.jsonPath().getString("msg");
		System.out.println(info);
		
		assertEquals(info,"Event deleted.");
		
	  System.out.println("-------------------END DELETE-------------------");
	}
}
