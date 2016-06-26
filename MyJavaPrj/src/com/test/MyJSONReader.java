package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MyJSONReader {

	public static void main(String[] args) {
		try {
			boolean enableCurrencyConversion = false;
			Map<String, String> usdConversionMap = new LinkedHashMap<String, String>();
			//URL jsonTestURL = new URL("http://ip.jsontest.com/");
			URL jsonTestURL = new URL("http://finance.google.com/finance/info?client=ig&q=BOM:532480");
			//URL jsonTestURL = new URL("http://rate-exchange.appspot.com/currency?from=USD&to=HKD");
			//URL jsonTestURL = new URL("http://finance.yahoo.com/d/quotes.csv?e=.csv&f=c4l1&s=USDEUR=X,USDGBP=X,USDHKD=X,USDINR=X,USDPKR=X");
			//http://rate-exchange.appspot.com/currency?from=USD&to=EUR
			//http://www.google.com/ig/api?stock=532480
			//http://finance.google.com/finance/info?client=ig&q=BOM:532480
			//http://finance.google.com/finance/info?client=ig&q=BOM%3a532480
			URLConnection gq = jsonTestURL.openConnection();
			//System.out.println("Ïnput Stream : " + gq.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(gq.getInputStream()));
			String line;
			String entireLine = ""; 
			while ((line = br.readLine()) != null) {
				if(enableCurrencyConversion) {
					String[] conversions = line.split("\\,");
					String currency = conversions[0].substring(1, conversions[0].length()-1);
					String rate = conversions[1];
					usdConversionMap.put(currency, rate);
				}
				entireLine += line;
			}
			if(!enableCurrencyConversion) {
				System.out.println("Response : " + entireLine);
				if(entireLine.contains("[")) {
					Object obj = JSONValue.parse(entireLine.substring(3));
					JSONArray finalResult = (JSONArray)obj;
					JSONObject arrayObj = (JSONObject)finalResult.get(0);
					System.out.println("Stock Info Start");
					for (Object object : arrayObj.keySet()) {
						System.out.println(object + " : " + arrayObj.get(object));
					}
					System.out.println("Stock Info End");
				} else {
					Object obj = JSONValue.parse(entireLine);
					JSONObject finalResult = (JSONObject)obj;
					System.out.println("Rate : " + finalResult.get("rate"));
					System.out.println("From : " + finalResult.get("from"));
					System.out.println("To   : " + finalResult.get("to"));
				}
			}
			if(enableCurrencyConversion) {
				System.out.println("Final USD Currenct Rates : " + usdConversionMap);
				for (String curreny : usdConversionMap.keySet()) {
					System.out.println("Curreny : " + curreny + " -> Rate : " + usdConversionMap.get(curreny));
				}
			}
			br.close();
		} catch (IOException io) {
			System.err.println("There was error in connection the google finance.");
		}
		/*
		BufferedReader br = null;
		List<String> updatedImagesURLs = new ArrayList<String>();
		try {
			String sCurrentLine;
			File updatedImagesURLFile = new File("C:\\Ashford\\images.txt");
			if(!updatedImagesURLFile.exists()) {
				
				return;
			}
			br = new BufferedReader(new FileReader(updatedImagesURLFile));
			while ((sCurrentLine = br.readLine()) != null) {
				updatedImagesURLs.add(sCurrentLine);
			}
			//invalidateCDNCacheForImages(updatedImagesURLs);
			System.out.println("updatedImagesURLs : " + updatedImagesURLs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		List<String> updImagesURLs = new ArrayList<String>();
		updImagesURLs.add("cat");
		updImagesURLs.add("dog");
		updImagesURLs.add("elephant");
		updImagesURLs.add("Lion");
		updImagesURLs.add("Modi");
		
		try {
			File file = new File("C:\\Ashford\\images.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < updImagesURLs.size(); i++) {
				bw.write(updImagesURLs.get(i) + (i < (updImagesURLs.size() -1) ? "\n" : ""));
			}
			bw.close();
			System.out.println("Done with writing the file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(getUpdateImageNotificationEmail(updImagesURLs));
		*/

	}
	
	public static String getUpdateImageNotificationEmail(List<String> pUpdatedImageURLs) {

		StringBuffer pEmailBody = new StringBuffer("UPDATED IMAGES IMPORT SUMMARY -----");
		pEmailBody.append("\n\n");
		pEmailBody.append("TOTAL UPDATED IMAGES : " + pUpdatedImageURLs.size());
		pEmailBody.append("\n\n");
		pEmailBody.append("UPDATED IMAGES PATH -----");
		pEmailBody.append("\n\n");

		for (String updatedmageURL : pUpdatedImageURLs) {
			pEmailBody.append("\t" + updatedmageURL+"\n");
		}
		pEmailBody.append("\n\nPlease Note : Execute the component HSWWCDNCacheInvalidator.invalidateImageCDNCacheFromFile method at the given below below path only after the Content Admin project is deployed.\n");
		pEmailBody.append(" http://prd-con01.hswwco.com:8080/dyn/admin/nucleus/hsww/integrations/catalog/processor/HSWWCDNCacheInvalidator/?shouldInvokeMethod=invalidateImageCDNCacheFromFile\n");
		pEmailBody.append("\n\n----- UPDATED IMAGES IMPORT SUMMARY END-----");

		return pEmailBody.toString();
	}
}