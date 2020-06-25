import static org.testng.Assert.assertEquals;

//import java.awt.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class ResponseHeaderTest extends BaseClass{
	//public static final String Base_URL = "https://api.github.com";
	
	//HttpClient client = HttpClientBuilder.create().build();	
	CloseableHttpClient client;
	CloseableHttpResponse response;
	
	ResponseUtil Ru = new ResponseUtil();
	
	
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
public void contetTypeIsJson() throws  IOException {
	
	HttpGet get = new HttpGet(Base_URL );
	  response = client.execute(get);
	  Header s = response.getEntity().getContentType();
	ContentType ct =   ContentType.getOrDefault(response.getEntity());
//	String statusCode = response.getEntity().getContentType();
	//assertEquals(s.getValue().contains("json"));
	//Assert.assertEquals(statusCode, 200);
	String mT=  ct.getMimeType();
	assertEquals(mT, "application/json");
	System.out.println(mT);
	
	
	}

@Test
public void serverIsGitHub() throws ClientProtocolException, IOException {
	HttpGet get = new HttpGet(Base_URL );
	  response = client.execute(get);
	  String server = Ru.getHeaderJava8Way(response, "server");
	  assertEquals(server, "GitHub.com");
}

/*@Test
public void eTagIspresent() throws ClientProtocolException, IOException {
	HttpGet get = new HttpGet(Base_URL );
	  response = client.execute(get);
	  
	  assertEquals(true, Ru.headerIsPresent(response,"etag"));
	  System.out.println("etag");
}*/

}



