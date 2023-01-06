package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
//import io.restassured.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestExampleTest {
	
	//public static String randomEmail() {
//		return RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@gmail.com";
//	}
	
		
	@Test
	public void postATodo() {
		// URL-ul unde vom face callul: https://keytodorestapi.herokuapp.com/
		
	//	System.out.println(randomEmail());
	//	System.out.println(randomEmail());
	//	System.out.println(randomEmail());
	//	System.out.println(randomEmail());
	//	System.out.println(randomEmail());

		
		//Json Object creat direct-versiunea1
		JSONObject requestPayload =new JSONObject();
		requestPayload.put("title", "Flori New Title");
		requestPayload.put("body", "Flori New Body");
		
		//sau obiect json luat din fisier -versiunea2
		File fisier= new File("data1.json");
		
		Response response=RestAssured
				.given()
				//	.header("Content-Type","application/json")
				//	.header("accept","application/json")
				.when()
				//	.body("{\"title\":\"flori\",\"body\":\"flori body\"}")
				//fie se scrie direct aci jsonul (ca linia de sus), fie printr-un obiect json ca mai jos
				 //  .body(requestPayload.toJSONString())
				.body(fisier)
					.post("https://keytodorestapi.herokuapp.com/api/save")
				.then()
				  .assertThat().statusCode(200)
				  .extract().response();
		
		System.out.println(response.asPrettyString());
		
		String id= response.jsonPath().getString("id"); //jsonPath permite sa intri in interiorul response
		System.out.println(id);
		String info= response.jsonPath().getString("info"); //jsonPath permite sa intri in interiorul response
		System.out.println(info);
		
		assertEquals(info,"Todo saved! Nice job!");
	}
	
	
}
