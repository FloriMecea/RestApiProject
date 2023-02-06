package tests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.NumberChecker.*;
import static utils.NumberIsPositive.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Curs7_Tema {
	
	//https://swapi.dev/api/people/1/

	@Test
	public void test() {
		Response resp=given().
				get("https://swapi.dev/api/people/1/").
				then().
				statusCode(200).extract().response();
		
		System.out.println(resp.asString());
		JsonPath jsonPath=resp.jsonPath();
		
		assertThat(jsonPath.getString("name"), equalTo("Luke Skywalker"));
		
		
		assertThat(jsonPath.getInt("height"), greaterThan(171));
		assertThat(jsonPath.getInt("mass"), lessThan(80));
		
       List<String> details= new ArrayList<>(); 
       details.add(jsonPath.getString("skin_color"));
       details.add(jsonPath.getString("eye_color"));
       details.add(jsonPath.getString("hair_color"));
		
	   assertThat(details,contains(equalTo("fair"),equalTo("blue"),equalTo("blond")));
	   
	   assertThat(jsonPath.getString("birth_year"),is(not(numberOnly())));
		
	   List<String> species= jsonPath.getList("species");
	   assertThat(species, is(empty()));
	   
	   List<String> vehicles= jsonPath.getList("vehicles");   
	   System.out.println(vehicles);
	   List<String> starships= jsonPath.getList("starships");
	   System.out.println(starships);
	   assertThat(starships, hasSize(starships.size()));
	   
	/*   for (String element:vehicles) {
		   assertThat(element,not(containsString("starship")));
	   } */
	   //compara 2 liste ca nu sunt egale
	   assertThat(starships.toString(),not (equalTo(vehicles.toString())));
	   
	}
 
	  
	
}
