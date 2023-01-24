package tests;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;

import static io.restassured.RestAssured.given;
import static utils.Curs8_Tema2_CustomMatcher.*;

public class Curs8_Tema2 {
	
	//https://swapi.dev/api/people/4/

	@Test (priority=1)
	public void test() {
		Response resp=given().
				get("https://swapi.dev/api/starships/3/").
				then().
				log().all()
				.statusCode(200).extract().response();
		
		System.out.println(resp.asString());
		JsonPath jsonPath=resp.jsonPath();
		
		assertThat(jsonPath.getString("name"), equalTo("Star Destroyer"));
	   
		List<String> films= jsonPath.getList("films"); 	
		assertThat(films,hasItems(is(oneOf("https://swapi.dev/api/films/2/", "https://swapi.dev/api/films/6/", "https://swapi.dev/api/films/5/"))));
		
		
		assertThat(jsonPath.getDouble("max_atmosphering_speed"), is(closeTo(1000,30)));
		
	   List<String> pilots= jsonPath.getList("pilots");
	   assertThat(pilots, is(empty()));
		
	   assertThat(films, is(not(empty())));
	
	   assertThat(jsonPath.getString("model"),both(containsString("Imperial")).and(containsString("Destroyer")));
	   
	   String capacity=jsonPath.getString("cargo_capacity");
	   System.out.println(capacity);
	   assertThat(capacity, is(cargoCapacity()));
	   
}  
	
}
