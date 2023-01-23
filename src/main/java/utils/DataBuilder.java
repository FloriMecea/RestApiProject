package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {

	  public static JSONObject buildUser() {
		  JSONObject bodyBuilder =  new JSONObject();
	Faker faker = new Faker();
	
	String email = faker.internet().emailAddress();
	bodyBuilder.put("name", faker.name().firstName());
	bodyBuilder.put("email",email );
	bodyBuilder.put("age", faker.number().numberBetween(5, 130));
	bodyBuilder.put("gender", "m");
	
	return bodyBuilder;
	  }
	
 public static JSONObject buildToDo() {
		  JSONObject todoBuilder =  new JSONObject();
	      Faker faker = new Faker();
	
	todoBuilder.put("title", faker.lordOfTheRings().character());
	todoBuilder.put("body", faker.dune().saying() );
	
	
	 return todoBuilder;
	  }

 public static JSONObject buildToken() {
	  JSONObject tokenBuilder =  new JSONObject();
     Faker faker = new Faker();

     tokenBuilder.put("username", "admin");
     tokenBuilder.put("password", "password123");

    return tokenBuilder;
 }
 
 public static JSONObject buildBooking() {
	 /*
	  * {
		    "firstname" : "Jim",
		    "lastname" : "Brown",
		    "totalprice" : 111,
		    "depositpaid" : true,
		    "bookingdates" : {
		        "checkin" : "2018-01-01",
		        "checkout" : "2019-01-01"
    	     },
         "additionalneeds" : "Breakfast"
         }
	  */
	 JSONObject booking = new JSONObject();
		booking.put("firstname" , "Flori");
		booking.put("lastname" ,"Doe");
		booking.put("totalprice" , 111);
		booking.put("depositpaid" , true);
			JSONObject bookingDates = new JSONObject();
			bookingDates.put("checkin" , "2023-01-01");
			bookingDates.put("checkout" , "2023-01-11");
		booking.put("bookingdates" , bookingDates);
		booking.put("additionalneeds" , "Breakfast"); 
    

   return booking;
}
 
 
 
		
}
