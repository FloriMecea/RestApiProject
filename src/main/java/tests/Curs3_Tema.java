package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Curs3_Tema {
	
	@Test
	public void postATodo() {
	
		//Json Object creat direct-versiunea1
	/*	JSONObject requestPayload =new JSONObject();
		requestPayload.put("id", 202);
		requestPayload.put("title", "Flori Book1");
		requestPayload.put("description", "Flori Book1 description");
		requestPayload.put("pageCount", 100);
		requestPayload.put("excerpt", "string");
		requestPayload.put("publishDate", "2022-01-05T14:38:50.541Z");
		
	*/
		
		//sau obiect json luat din fisier -versiunea2
		File fisier= new File("data_tema3.json");
	//	File fisier1= new File("data_tema3_multiple entries.json");
		
		
		Response response=RestAssured
				.given()
					.header("Content-Type","application/json")
			
				.when()
				//fie se scrie direct aci jsonul (ca linia de sus), fie printr-un obiect json ca mai jos
				//  .body(requestPayload.toJSONString())
				.body(fisier)
			//	.body(fisier1)
					.post("https://fakerestapi.azurewebsites.net/api/v1/Books")
				.then()
				  .assertThat().statusCode(200)
				  .extract().response();
		
		System.out.println(response.asPrettyString());
		
		String id= response.jsonPath().getString("id"); //jsonPath permite sa intri in interiorul response
		System.out.println(id);
		
		assertEquals(id,"202");
	}
	
}
