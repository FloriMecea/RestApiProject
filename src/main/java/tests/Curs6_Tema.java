package tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent_Tema_Curs6;

public class Curs6_Tema extends BaseComponent_Tema_Curs6{
    String id, name1;
 
	

	
	@Test(priority = 1)
	public void getAllBooks() {
		System.out.println("-----------------------Start Get request---------------------");
		
		Response response = doGetAllRequest("api/v1/Books", 200);
		
		//JSONPath
		JsonPath jsonPath=response.jsonPath();
		//it este nodul curent
		List<String> allBooks =jsonPath.getList("findAll{it.pageCount >1000 &it.pageCount<=2000}.title");
		System.out.println(allBooks);
		System.out.println(allBooks.size());
		
	//	System.out.println(jsonPath.getString("title"));
		assertEquals(allBooks.size(),10);
		System.out.println("-----------------------End Get request---------------------");
	}
	
}
