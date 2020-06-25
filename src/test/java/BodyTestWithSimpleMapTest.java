import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BodyTestWithSimpleMapTest extends BaseClass{
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
	public void retrunsCorrectLogin() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(Base_URL + "/users/andrejss88");
		response = client.execute(get);
		String jsonBody = EntityUtils.toString(response.getEntity());
		//System.out.println(jsonBody);
		
		JSONObject jsonObject = new JSONObject(jsonBody);
		String loginValue = (String)getValueFor(jsonObject, "login");
		assertEquals(loginValue, "andrejss88");
	}

	private Object getValueFor(JSONObject jsonObject, String key) {
		// TODO Auto-generated method stub
		return jsonObject.get(key);
	}

}
