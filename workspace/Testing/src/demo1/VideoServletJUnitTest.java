package demo1;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;

public class VideoServletJUnitTest {

	String url = "http://192.168.52.232:8080/Testing/Servlet1";
	// HttpPost request = new HttpPost(url);
	//List data<NameValuePair> = new ArrayList<NameValuePair>();
	HashMap hashMap = new HashMap();
	@Test
	public void test() {
		HttpResponse response;
		String output = "";
		HttpEntity content = null;
		InputStream in;
		HttpPost request = new HttpPost(url);
		hashMap.put("name", "Rishi");
		hashMap.put("type", "1");
		hashMap.put("uploader", "upload");
		hashMap.put("date", "20th May 2016");
	}

}
