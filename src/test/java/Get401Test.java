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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Get401Test extends BaseClass{
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
	
	
	@DataProvider
	private Object[][] endpoints(){
		return new Object[][] {
			
			{"/user"},
			{"/user/followers"},
			{"/notifications"}
	};
	}
	
@Test(dataProvider = "endpoints")
public void baseUrlReturns401(String endpoint) throws ClientProtocolException, IOException {
	
	HttpGet get = new HttpGet(Base_URL + endpoint);
	  response = client.execute(get);
	int statusCode = response.getStatusLine().getStatusCode();
	assertEquals(statusCode, 401);
	//Assert.assertEquals(statusCode, 200);
	response.getEntity().getContentType();
	
}


}

