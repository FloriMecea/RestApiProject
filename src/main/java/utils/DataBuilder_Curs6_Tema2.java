package utils;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder_Curs6_Tema2 {

	  public static JSONObject buildUser() {
		  JSONObject bodyBuilder =  new JSONObject();
	Faker faker = new Faker();
	
	//String email = faker.internet().emailAddress();
	bodyBuilder.put("body", faker.chuckNorris().fact());
	bodyBuilder.put("title", faker.name().firstName());
	//bodyBuilder.put("email",email );
	//bodyBuilder.put("age", faker.number().numberBetween(5, 130));
	//bodyBuilder.put("gender", "m");
	
//	System.out.println(bodyBuilder);
	
    try(FileWriter file= new FileWriter("todo.json")){
		   file.write(bodyBuilder.toJSONString());
		   
	}catch (IOException e) {
		   e.printStackTrace();
	   }
	
	return bodyBuilder;
	  }
			  

		
}
