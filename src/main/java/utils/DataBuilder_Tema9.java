package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder_Tema9 {

	
 public static JSONObject buildToDo() {
		  JSONObject todoBuilder =  new JSONObject();
	      Faker faker = new Faker();
	
	todoBuilder.put("title", faker.lordOfTheRings().character());
	todoBuilder.put("body", faker.dune().saying() );
	
	
	 return todoBuilder;
	  }

 public static JSONObject buildUser() {
	  JSONObject tokenBuilder =  new JSONObject();
     Faker faker = new Faker();

     tokenBuilder.put("user", "flori");
     tokenBuilder.put("pass", "mypassword");

    return tokenBuilder;
 }
		
}
