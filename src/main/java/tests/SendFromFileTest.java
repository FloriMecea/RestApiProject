package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;

public class SendFromFileTest extends BaseComponent2{

//	@Test
	public void testJsonFile() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("data2_curs7.json"));
		JSONArray todoList = (JSONArray) obj;
		
		System.out.println(todoList);
		System.out.println(todoList.get(0));
		
		for(Object totdo : todoList) {
			JSONObject objTodo = (JSONObject)totdo;
			Response resp = doPostRequest(objTodo.toJSONString());
			System.out.println(resp.asString());
		}
	}
	
	@Test
	public void parseJson() throws IOException, ParseException {
		//1. parser de json
		JSONParser parser = new JSONParser();
		
		//2. Incarcam fisierul json
		FileReader reader =new FileReader("data3_curs6.json");
		
		//3. parsam fisierul
		Object obj = parser.parse(reader);
		
		// 4. Punem continutul citit/parsat din fisier in JSonArray 
			JSONArray employeeList = (JSONArray) obj;
				
			System.out.println(employeeList);
			
		//5. Luam un obiect Json individual din JSONArray (JSONArray este compus din JSONObject
			//cu json object ca sa ajungi la un element de te intereseaza e nevoie sa faci mai multi pasi decat la json path
			JSONObject employeeObject= (JSONObject) employeeList.get(0);  //pasul1
			System.out.println(employeeObject);
			
			JSONObject employeeAttribute= (JSONObject) employeeObject.get("employee"); //pasul 2
			System.out.println(employeeAttribute.get("company"));  //pasul3
			
			System.out.println("---------------------------------");
			
			File jsonFile=new File("data3_curs7.json");
			JsonPath jsonPath=JsonPath.from(jsonFile);
			System.out.println(jsonPath.getString("[0]"));
			System.out.println(jsonPath.getString("[0].employee.company"));  //pe cand la json path poti face asta dintr-un singur pas
			
			//folosim datatype JSONObject cand vrem sa cream/trimitem 
			//cand vrei sa asertezi/citesti se foloseste jspath -asert de pe post sau get request
			//cand vrei sa trimiti un obiect pe post atunci folosesti json object
			
	}
}
