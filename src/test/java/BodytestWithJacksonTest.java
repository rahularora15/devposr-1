import static org.testng.Assert.assertEquals;

import java.io.IOException;

import javax.swing.text.html.parser.Entity;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.NotFound;
import entities.RateLimit;
import entities.User;

public class BodytestWithJacksonTest extends BaseClass{
	//public static final String Base_URL = "https://api.github.com";
	
	//HttpClient client = HttpClientBuilder.create().build();	
	CloseableHttpClient client;
	CloseableHttpResponse response;
	//ResponseUtil Ru = new ResponseUtil();
	
	@BeforeTest
	public void setup() {
		 client = HttpClientBuilder.create().build();	
	}
	
	@AfterTest
	public void tearDown() throws IOException {
		client.close();
		response.close();
	}
	
	@Test
	public void returnCorrectLogin() throws ClientProtocolException, IOException {
		HttpGet get= new HttpGet(Base_URL + "/users/andrejss88");
	   response = client.execute(get);
	   
	  User user =  ResponseUtil.unmarshall(response,User.class);
	  assertEquals(user.getLogin(), "andrejss88");
	}
	
	@Test
	public void returnCorrectId() throws ClientProtocolException, IOException {
		HttpGet get= new HttpGet(Base_URL + "/users/andrejss88");
	   response = client.execute(get);
	   
	  User user =  ResponseUtil.unmarshallGeneric(response,User.class);
	  assertEquals(user.getId(), 11834443);
	}

	
	@Test
	public void notFoundMessageIsCorrect() throws ClientProtocolException, IOException {
		HttpGet get= new HttpGet(Base_URL + "/nonexist");
		   response = client.execute(get);
		   NotFound nf = 	ResponseUtil.unmarshallGeneric(response, NotFound.class);
		   assertEquals(nf.getMessage(), "Not Found");
	}
	
	
	@Test
	public void rateLimits() throws ClientProtocolException, IOException {
		HttpGet get= new HttpGet(Base_URL + "/rate_limit");
		   response = client.execute(get);
		   RateLimit rl = 	ResponseUtil.unmarshallGeneric(response, RateLimit.class);
		   assertEquals(rl.getCoreLimit(), "60");
	}

}
