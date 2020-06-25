package testMaven;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestGet200Test  extends BaseClass{
	//public static final String Base_URL = "https://api.github.com";
	
	//HttpClient client = HttpClientBuilder.create().build();	
	CloseableHttpClient client;
	CloseableHttpResponse response;
	
	
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
public void baseUrlReturns200() throws ClientProtocolException, IOException {
	
	HttpGet get = new HttpGet(Base_URL);
	  response = client.execute(get);
	int statusCode = response.getStatusLine().getStatusCode();
	assertEquals(statusCode, 200);
	//Assert.assertEquals(statusCode, 200);
	
}

@Test
public void rateLimit() throws ClientProtocolException, IOException {
	
	HttpGet get = new HttpGet(Base_URL+ "/rate_limit");
	HttpResponse  response = client.execute(get);
	int statusCode = response.getStatusLine().getStatusCode();
	Assert.assertEquals(statusCode, 200);
	
}


}
