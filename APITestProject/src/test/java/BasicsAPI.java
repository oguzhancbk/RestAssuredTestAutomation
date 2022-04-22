import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsabelMethods;
import files.payload;

public class BasicsAPI {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// PosttPlace
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.Addplace()).
		post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
		
		//UpdatePlace  
		String newAddress = "Summer walk , Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("/maps/api/place/update/json").then().assertThat().statusCode(200).log().all().body("msg", equalTo("Address successfully updated"));
		
		
		//GetPlace
		
		String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json").then().assertThat().log().all()
		.statusCode(200).extract().response().asString();
		JsonPath js1 = ReUsabelMethods.rawToJson(getPlaceResponse);
		String actualAdress = js1.getString("address");
		System.out.println(actualAdress);
		Assert.assertEquals(actualAdress, newAddress);
		
		
		
	}

}
