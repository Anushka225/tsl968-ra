package Day1;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
public class PositiveTest {
	String id;
  @Test(enabled=false,description="for getting all contact List")
  public void getAllContactList() {
	  given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  @Test(enabled=true,description="Adding contacts")
  public void addContact() {
	  JSONObject loc=new JSONObject();
	  loc.put("city", "Pune");
	  loc.put("country", "India");
	  
	  JSONObject emp=new JSONObject();
	  emp.put("jobTitle", "Automation Tester");
	  emp.put("company", "LTI");
	  
	  JSONObject ob=new JSONObject();
	  ob.put("firstName", "Mayank");
	  ob.put("lastName", "Sharma");
	  ob.put("email", "asmith@thinkingtester.com");
	  ob.put("location",loc );
	  ob.put("employer",emp);
	  
	  id=given()
	  .header("Content-Type","application/json")
	  .body(ob.toJSONString())//to convert object to json type
	.when()
	  .post("http://3.13.86.142:3000/contacts/")
	.then()
	  .log()
	  .body()
	  .statusCode(200)
	  .extract()
	  .jsonPath()
	  .get("_id");
	  System.out.println("ID is "+id);
  }
  @Test(enabled=true,dependsOnMethods="addContact",description="getting specific contact")
  public void getSpecificContact() {
	  given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
	  
  }
  @Test(enabled=true,dependsOnMethods="addContact",description="updating contact")
  
  public void updateContact() {
	  JSONObject loc=new JSONObject();
	  loc.put("city", "Pune");
	  loc.put("country", "India");
	  
	  JSONObject emp=new JSONObject();
	  emp.put("jobTitle", "Automation Tester");
	  emp.put("company", "LTI");
	  
	  JSONObject ob=new JSONObject();
	  ob.put("firstName", "Maya");
	  ob.put("lastName", "Sharma");
	  ob.put("email", "asmith@thinkingtester.com");
	  ob.put("location",loc );
	  ob.put("employer",emp);
	  
	  given()
	  .header("Content-Type","application/json")
	  .body(ob.toJSONString())//to convert object to json type
	.when()
	  .put("http://3.13.86.142:3000/contacts/"+id)
	.then()
	  .log()
	  .body()
	  .statusCode(204);
	  
  }
  @Test(enabled=true,dependsOnMethods="updateContact",description="deleting contact")
  public void deleteSecificContact() {
	  given()
	  .when()
	  .delete("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(204);
  }
}
