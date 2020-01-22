package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {


	String propResponse;
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if(req==null) {
			PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).
					setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\bpd8768\\eclipse-workspace\\APIAutomation\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);

		return prop.getProperty(key);	
	}

	public String getJsonPath(Response response, String key) {

		String resp = response.asString();
		int updated = resp.indexOf("{");
		propResponse=resp.substring(updated);
		System.out.println("*****final response**** "+propResponse);
		JsonPath js = new JsonPath(propResponse);
		return js.get(key);
	}
}
