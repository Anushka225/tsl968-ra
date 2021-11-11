package Day1;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GitHub {
  @Test(enabled=false,description="Getting all repositories")
  public void getAllRepo() {
	  
	  given()
	  .auth()
	  .oauth2("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")
	  .when().get("https://api.github.com/user/repos")
	  .then()
	  .log()
	  .body()
	  .statusCode(200)
	  .time(Matchers.lessThan(2000L),TimeUnit.MILLISECONDS);
  }
  @Test(enabled=true,description="adding repositories")
  public void addRepo() {
	  JSONObject js=new JSONObject();
	  js.put("name", "tsl968-restAssured");
	  js.put("description","I am created by RestAssured");
	  js.put("homepage", "http://github.com/Anushka225");;
	  given()
	  .auth()
	  .oauth2("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")
	  .header("Content-Type","application/json")
	  .body(js.toJSONString())
	  .when()
	  .post("https://api.github.com/user/repos")
	  .then()
	  .log()
	  .body()
	  .statusCode(201)
	  .time(Matchers.lessThan(5000L),TimeUnit.MILLISECONDS);
  }
}
