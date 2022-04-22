package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type","application/json").body(payload.Addbook(isbn,aisle))
				.when().post("Library/Addbook.php").then().log().all().assertThat()
		.statusCode(200).extract().response().asString();
		
		
		JsonPath js = ReUsabelMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
		
		
	}
@DataProvider(name = "BooksData")
public Object[][] getData() 
{
	return new Object[][] { {"ojwtc","9393"} , {"cwteee","4255"} , {"okkasd","533"} };
	
	
}
	
}
