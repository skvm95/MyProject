package com.ashford.cdn;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonSimpleExample {

	private static String algorithm = "HmacSHA256";

	public static void main(String[] args) {

		JSONObject obj = new JSONObject();
		obj.put("emailType", "www.ashford.com");
		obj.put("emailSubject", "Purged Cache for newly updated images on the site");
		obj.put("emailTo", "sandeep.mishra@logixal.com");
		obj.put("emailCC", "cfrankel@AllianceTime.com");
		obj.put("emailBCC", "kmaqsudi@ashford.com");

		JSONObject entry1 = new JSONObject();
		entry1.put("shortname", "image1");
		entry1.put("shortname", "www.ashford.com/images/catalog/rado/ashford/678124224_FSA.jpg");
		entry1.put("regex", "false");
		entry1.put("delete", "false");

		JSONObject entry2 = new JSONObject();
		entry2.put("shortname", "image1");
		entry2.put("shortname", "www.ashford.com/images/catalog/rado/ashford/678423364_FSA.jpg");
		entry2.put("regex", "false");
		entry2.put("delete", "false");

		JSONArray entriesList = new JSONArray();
		entriesList.add(entry1);
		entriesList.add(entry2);

		obj.put("entries", entriesList);

		JSONObject callback1 = new JSONObject();
		callback1.put("callbackType", "request");
		callback1.put("callbackUrl", "www.ashford.com/images/catalog/rado/ashford/678124224_FSA.jpg");

		JSONObject callback2 = new JSONObject();
		callback2.put("callbackType", "request");
		callback2.put("callbackUrl", "www.ashford.com/images/catalog/rado/ashford/678423364_FSA.jpg");

		JSONArray callbacksList = new JSONArray();
		callbacksList.add(callback1);
		callbacksList.add(callback2);

		obj.put("callbacks", callbacksList);


		String key = "52fb9c69ea5fe1b82950b4fcb211574132e225a0d7233cfda8eaa0407a422663";
		String username = "smishra";
		String timestamp = Long.toString(System.currentTimeMillis());
		//String shortname = "hsww";
		String uri = "http://www.ashford.com/purge-api/v1/request";
		//String uri = "http://control.llnw.com/purge-api/v1/request";
		// Calculating digest
		String digest = null;
		HttpClient httpClient = new DefaultHttpClient();

		try {
			digest = new String(Hex.encodeHex(generateMac(Hex.decodeHex(key.toCharArray()), ("GET" + uri + timestamp).getBytes("UTF-8"))));
			System.out.println("Digest : " + digest);
			HttpPost request = new HttpPost(uri);
			StringEntity params =new StringEntity(obj.toJSONString());
			request.addHeader("Accept", "application/json");
			request.addHeader("Content-Type", "application/json");
			request.addHeader("X-LLNW-Security-Token", digest);
			request.addHeader("X-LLNW-Security-Principal", username);
			request.addHeader("X-LLNW-Security-Timestamp", timestamp);
			request.setEntity(params);
			System.out.println("Request  : " + request);
			Header[] headers = request.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println("Header [" + i + "] : " + headers[i]);
			}
			HttpResponse response = httpClient.execute(request);			
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			String entireLine = ""; 
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				entireLine += line;
			}
			System.out.println("Response : " + entireLine);
			FileWriter file = new FileWriter("C:/Ashford/test.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (DecoderException e1) {
			e1.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		System.out.print(obj);

	}

	private static byte[] generateMac(byte[] sharedKey, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException {
		SecretKeySpec keySpec = new SecretKeySpec(sharedKey, algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.reset();
		mac.init(keySpec);
		return mac.doFinal(data);
	}
}