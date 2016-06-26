package com.ashford.cdn;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FlushCDNCacheService {

	private static String algorithm = "HmacSHA256";

	public static void main(String[] args) throws IOException, InterruptedException, InvalidKeyException, NoSuchAlgorithmException, DecoderException {

		JSONObject obj = new JSONObject();
		obj.put("emailType", "www.ashford.com");
		obj.put("emailSubject", "Purged Cache for newly updated images on the site");
		obj.put("emailTo", "sandeep.mishra@logixal.com");
		obj.put("emailCC", null);
		obj.put("emailBCC", null);

		JSONObject entry1 = new JSONObject();
		entry1.put("shortname", "hsww");
		entry1.put("url", "http://www.ashford.com/images/catalog/jaquet-droz/ashford/J001120112_FXA.jpg");
		entry1.put("regex", "false");
		entry1.put("delete", "false");
		
		JSONObject entry2 = new JSONObject();
		entry2.put("shortname", "hsww");
		entry2.put("url", "http://www.ashford.com/images/catalog/fossil/georgia/ES3410_FMZ.jpg");
		entry2.put("regex", "false");
		entry2.put("delete", "false");
		
	

		JSONArray entriesList = new JSONArray();
		entriesList.add(entry1);
		entriesList.add(entry2);


		obj.put("entries", entriesList);

		JSONObject callback1 = new JSONObject();
		callback1.put("callbackType", "request");
		callback1.put("callbackUrl", "http://www.ashford.com/images/catalog/jaquet-droz/ashford/J001120112_FXA.jpg");
		
		JSONObject callback2 = new JSONObject();
		callback2.put("callbackType", "request");
		callback2.put("callbackUrl", "http://www.ashford.com/images/catalog/fossil/georgia/ES3410_FMZ.jpg");
		


		JSONArray callbacksList = new JSONArray();
		callbacksList.add(callback1);
		callbacksList.add(callback2);


		obj.put("callbacks", callbacksList);
		
		String requestBody = obj.toJSONString();
		System.out.println("requestBody : " + requestBody);

		HttpClient client = new HttpClient();
		
		String key = "20a08dc99e5c0224362509a97bdfc8ff0f65e7fd2a4e26c661831386d7418eaf";
		String username = "skumar";
		String timestamp = Long.toString(System.currentTimeMillis());
		String uri = "http://control.llnw.com/purge-api/v1/request";
		// Calculating digest
		String digest = new String(Hex.encodeHex(generateMac(Hex.decodeHex(key.toCharArray()), ("POST" + uri + timestamp + requestBody).getBytes("UTF-8"))));

		PostMethod method = new PostMethod(uri);
		method.addRequestHeader("Accept", "application/json");
		method.addRequestHeader("Content-Type", "application/json");
		method.addRequestHeader("X-LLNW-Security-Token", digest);
		method.addRequestHeader("X-LLNW-Security-Principal", username);
		method.addRequestHeader("X-LLNW-Security-Timestamp", timestamp);
		method.setRequestBody(requestBody);

		InputStream is = null;
		try {
			int rc = client.executeMethod(method);
			if (rc != HttpStatus.SC_OK) {
				/* Handle errors with the API communication */
			}
			// Obtaining and printing response result
			is = method.getResponseBodyAsStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer);
			String theString = writer.toString();
			System.out.println(theString);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
	}

	private static byte[] generateMac(byte[] sharedKey, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException {
		SecretKeySpec keySpec = new SecretKeySpec(sharedKey, algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.reset();
		mac.init(keySpec);
		return mac.doFinal(data);
	}
}