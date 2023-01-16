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
		
		assertThat(films,contains("https://swapi.dev/api/films/1/", 
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
		//verificam daca exista un anumit item in colectie
		assertThat(films,hasItem("https://swapi.dev/api/films/5/"));
		//verificam daca exista mai multe obiecte dar nu toate
		assertThat(films,hasItems("https://swapi.dev/api/films/5/","https://swapi.dev/api/films/4/","https://swapi.dev/api/films/3/"));

		assertThat(films,hasItem(startsWith("https")));
      // cauta in colectie daca exista un item care se termina in "4/", un alt item sau acelasi care sa inceapa cu "https" si alt item care sa contina "swapi"
		assertThat(films,hasItems(endsWith("4/"),startsWith("https"),containsString("swapi")));

		assertThat(films, hasItems(endsWith("4/"), startsWith("http"), endsWith("/6/"), containsString("swapi")));	
		assertThat(films, hasSize(5));
		assertThat(films, hasSize(lessThan(10)));
		assertThat(films, hasSize(greaterThan(3)));
		
		assertThat(films.get(0), containsString("1"));
		assertThat(films, hasToString(containsString("5")));
		
		assertThat (films, both(hasSize(lessThan(10))).and(hasToString(containsString("https"))));
		  films.clear();
		  assertThat(films, is(empty()));
		  assertThat(films, is(emptyCollectionOf(String.class)));
	
		  //array: 
			String[] array={"https://swapi.dev/api/films/1/", 
			        "https://swapi.dev/api/films/3/", 
			        "https://swapi.dev/api/films/4/", 
			        "https://swapi.dev/api/films/5/", 
			        "https://swapi.dev/api/films/6/"};
			
	    assertThat(array,is(not(emptyArray())));
	    assertThat(array,is(not(nullValue())));
	    
	   //array care contine in ordinea data
	    assertThat(array,arrayContaining("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
	    
	    //array care contine in orice ordine
	    assertThat(array,arrayContainingInAnyOrder("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/6/", 
		        "https://swapi.dev/api/films/5/"));
	    
	    //Strings --> any order
	    assertThat(resp.asString(),stringContainsInOrder("rotation_period","orbital_period","diameter"));
	
	    //pattern pt stringuri
	    assertThat(name, matchesPattern("[A-Za-z]+"));
	    name="Tatooine23";
	    assertThat(name, matchesPattern("[A-Za-z0-9]+"));
	    name="23";
	    assertThat(name, matchesPattern("[0-9]+"));
	    
	    
	    //custom matchers
	    String rotation_period=jsonPath.getString("rotation_period");	    
	    String climate=jsonPath.getString("climate");    
	    String diameter=jsonPath.getString("diameter");
	    
	    assertThat(rotation_period,numberOnly());
	    assertThat(rotation_period,is(numberOnly()));
	    
	    //  assertThat(climate,numberOnly()); -daca vrem sa pice sa vedem mesajul scris decomentam linia asta
	    assertThat(climate,is(not(numberOnly())));
	    
	    assertThat(Integer.parseInt(diameter),is(numberPositive()));
	
	    name="Tatooine";
	    //AND
	    assertThat(name,both(containsString("a")).and(containsString("oo")));
    
	    //OR
	    assertThat(name, either(is("Tatooine")).or(is("Tatooine123")).or(is("Earth")));
	
	}
 
	  
	
}
