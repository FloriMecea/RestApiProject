package tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;

public class JsonPathExample extends BaseComponent2 {

	@Test
	public void jsonPathExamples() {
		Response result= doGetAllRequest();
		
		System.out.println(result.asString());
		assertTrue(result.asString().contains("Favian11"));
		
		//JSONPath
		JsonPath jsonPath=result.jsonPath();
		//citeste size-ul obiectului array
		System.out.println(jsonPath.getString("users.size()"));
		//read index based
		System.out.println(jsonPath.getString("users[0]"));
		//get name from first object of the array
		System.out.println(jsonPath.getString("users[0].name"));
		//toate numele din array
		System.out.println(jsonPath.getString("users.name"));
		System.out.println(jsonPath.getString("users.email"));
		System.out.println(jsonPath.getString("users._id"));
		System.out.println("Gender :  --> " + jsonPath.getString("users.gender"));
		
		//it este nodul curent
		List<String> allMales =jsonPath.getList("users.findAll{it.gender == 'm'}.gender");
		System.out.println(allMales);
		
		//afiseaza toate campurile gender care au gender "f" 
		List<String> allFemales =jsonPath.getList("users.findAll{it.gender == 'f'}.gender");
		System.out.println(allFemales);
		
		//afiseaza toate campurile gender care au gender "f" 
		List<String> allFemales1 =jsonPath.getList("users.findAll{it.gender == 'f'}");
		System.out.println(allFemales1);
		
		
		List<String> allBobo =jsonPath.getList("users.findAll{it.name == 'Bobo'}.name");
		System.out.println(allBobo);
		
		String user=jsonPath.getString("users.find{it.name=='Bobo' & it.age==12}.email");
		System.out.println(user);
		
		String user1=jsonPath.getString("users.find{it.name=='Bobo' & it.age>14}.email");
		System.out.println(user1);
		
		//OR conditions
		List<String> usersNamed =jsonPath.getList("users.findAll{it.name == 'Bobo' | it.name=='Kathey'}.name");
		System.out.println(usersNamed);
		
		List<String> ageLessThan =jsonPath.getList("users.findAll{it.age<45}._id");
		System.out.println(ageLessThan);
		
	}
}
