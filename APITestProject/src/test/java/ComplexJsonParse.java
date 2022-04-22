import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args ) {
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		// 1. Print No of courses returned by API
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		// 2.Print Purchase Amount
		
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		// 3. Print Title of the first course
		
		String tittlefirstcourses = js.getString("courses[0].title");
		System.out.println(tittlefirstcourses);
		
		// 4. Print All course titles and their respective Prices
		
		for(int i=0;i<count;i++) {
			
			System.out.println(js.get("courses["+i+"].tittle")+" = "+js.get("courses["+i+"].price").toString());
		}
		
		// 5. Print no of copies sold by RPA Course
		
		System.out.println(js.get("courses[2].copies").toString());
		
		// or
		
		for(int i = 0 ; i < count ; i++) {
			String courseTittles = js.get("courses["+i+"].tittle");
			if(courseTittles.equalsIgnoreCase("RPA")) {
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
			
		}
		
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		
		
	}

}
