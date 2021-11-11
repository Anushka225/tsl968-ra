package Day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherApi{
	@Test(enabled=false,description="getting weather API information generally")
	public void getWeatherInfo1() {

		RestAssured.given()
		         .when()
				.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=847912c0e308deac0367dd086867b379").then()
				.log()
				.body()
				.statusCode(200);
	}




	@Test(enabled=false,description="getting weather API information generally")
	public void getWeatherInfo2() {
        
		Response res=RestAssured.given()
		         .when()
				.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=847912c0e308deac0367dd086867b379");
		System.out.println(res.prettyPrint());
		System.out.println(res.getTime());
		System.out.println(res.getStatusCode());
		System.out.println(res.getContentType());
				
	
	}




	@Test(enabled=true,description="getting weather API information generally")
	public void getWeatherInfo3() {
        Map<String,String> param=new HashMap<String,String>();
		param.put("q", "Mumbai");
		param.put("appid","847912c0e308deac0367dd086867b379");
        RestAssured.given()
		         //.queryParam("q", "Mumbai")
		         //.queryParam("appid","847912c0e308deac0367dd086867b379")
		        .formParams(param) 
                .when()
				.get("http://api.openweathermap.org/data/2.5/weather")
				.then()
				.log()
				.body()
				.statusCode(200);
	}

}
