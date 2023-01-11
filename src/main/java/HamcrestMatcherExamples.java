import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class HamcrestMatcherExamples {
	
	//https://swapi.dev/api/planets/1/

	@Test
	public void test() {
		Response resp=given().
				get("https://swapi.dev/api/planets/1/").
				then().
				statusCode(200).extract().response();
		
		System.out.println(resp.asString());
		JsonPath jsonPath=resp.jsonPath();
		
		assertThat(jsonPath.getString("name"), equalTo("Tatooine"));
		String name=jsonPath.getString("name");
		assertThat(name,is(equalTo("Tatooine")));
		assertThat(name,is(("Tatooine")));
		assertThat(resp.asString(), is((instanceOf(String.class))));
		
		//not
		assertThat(name, is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		
		//start-width
		assertThat(resp.asString(),startsWith("{\"name"));
		assertThat(resp.asString(),startsWithIgnoringCase("{\"NAME"));
		
		//ends-width
		assertThat(resp.asString(),endsWith("planets/1/\"}"));
		assertThat(resp.asString(),endsWithIgnoringCase("plaNEts/1/\"}"));
		
		//ContainsString
		assertThat(jsonPath.getString("created"),containsString(":"));
		assertThat(name,containsStringIgnoringCase("TOOine"));
		
		List<String> movie= jsonPath.getList("films");
		System.out.println(movie);
		List<String> films= new ArrayList<>(movie); 
		
		assertThat(films,contains(        "https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, contains(
				startsWith("https"),
		        endsWith("3/"),
		        equalTo("https://swapi.dev/api/films/4/"),
		        startsWith("https://swapi.dev"),
		        endsWith("api/films/6/")));
	}
}
