import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.User;

public class ResponseUtil {
	public String getHeaders(CloseableHttpResponse response , String headerName) {
		Header[] headers = response.getAllHeaders();
		String returnHeader = "";
		List<Header> httpheaders = Arrays.asList(headers);
		
		for(Header header: httpheaders) {
			if(headerName.equalsIgnoreCase(header.getName()))  
				returnHeader= header.getValue();
		}
		//return string;
		if (returnHeader.isEmpty()) {
			throw new RuntimeException("Didn't find the header" + headerName);
		}
		
		return returnHeader;
		
		
	}
	
	public String getHeaderJava8Way(CloseableHttpResponse response , String headerName) {
		Header[] headers = response.getAllHeaders();
		
		List<Header> httpheaders = Arrays.asList(headers);
		//Header matchedHeader = httpheaders.stream().filter(headerName.equalsIgnoreCase(header.getName())).findFirst().orElseThrow(())
		Header matchedHeader=httpheaders.stream().filter(header->headerName.equalsIgnoreCase(header.getName())).findFirst().orElseThrow(()-> new RuntimeException("Didn't find the header"));
		return matchedHeader.getValue();
	}
	
	public  boolean headerIsPresent(CloseableHttpResponse response , String headerName) {
		Header[] headers = response.getAllHeaders();
		
		List<Header> httpheaders = Arrays.asList(headers);
		
		return httpheaders.stream().anyMatch(header->header.getName().equalsIgnoreCase(headerName));
	}
	
	public static User unmarshall(CloseableHttpResponse response, Class<User> class1) throws ParseException, IOException {
		// TODO Auto-generated method stub
		String jsonBody = EntityUtils.toString(response.getEntity());
		
		return new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, class1);
		
	}
	
	public static <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> class1) throws ParseException, IOException {
		// TODO Auto-generated method stub
		String jsonBody = EntityUtils.toString(response.getEntity());
		
		return new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, class1);
		
	}
}
