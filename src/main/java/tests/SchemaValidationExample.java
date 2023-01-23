package tests;

import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationExample {

	@Test
	public static void validateSchema() {
		Response result=given()
				.get("https://keytrcrud.herokuapp.com/api/users/63c5977d1941aa0015fa181b")
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		
		System.out.println(result.asString());
		assertThat(result.asString(),matchesJsonSchemaInClasspath("data_curs8.json"));
	}
}
