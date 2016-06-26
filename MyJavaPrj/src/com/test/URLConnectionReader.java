package com.test;

import java.net.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class URLConnectionReader {

    public static void main(String[] args) throws Exception {
    	//System.out.println("Retrieving the conversion rates.");
    	try {
    		
    		URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=USDINR=X&s=USDKRW=X&s=USDHKD=X");
    		//URL yahoo = new URL("http://rate-exchange.appspot.com/currency?from=USD&to=HKD");
    		URLConnection yc = yahoo.openConnection();
    		//System.out.println("Content : " + yc.getContent());
    		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
    		String inputLine = in.readLine();
    		
    		//System.out.println("Response : " + inputLine);
    		String[] response = inputLine.split("\\,");
    		/*
    		System.out.println("Response size : " + response.length);
    		for (int i = 0; i < response.length; i++) {
    			System.out.println("response : " + response[i]);
    		}*/
    		System.out.println("1 USD = " + response[1] + " HDK");
    		
    		ArrayList<String> currencyList = new ArrayList<String>();
    		currencyList.add("INR");
    		currencyList.add("KRW");
    		currencyList.add("HKD");
    		currencyList.add("PKR");
    		currencyList.add("BDT");
    		
    		
    		HashMap<String, String> conversionMap = new HashMap<String, String>();
    		
    		for (String currency : currencyList) {
    			
    			String url = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=USD{0}=X";
    			url = MessageFormat.format(url, currency);
    			//System.out.println("url : " + url);
    			
    			URL yahooUrl = new URL(url);
        		
        		//URL yahoo = new URL("http://rate-exchange.appspot.com/currency?from=USD&to=HKD");
        		URLConnection ycon = yahooUrl.openConnection();
        		//System.out.println("Content : " + yc.getContent());
        		BufferedReader br = new BufferedReader(new InputStreamReader(ycon.getInputStream()));
        		String outputLine = br.readLine();
        		
        		//System.out.println("Response : " + outputLine);
        		String[] res = outputLine.split("\\,");
        		/*
        		System.out.println("Response size : " + response.length);
        		for (int i = 0; i < response.length; i++) {
        			System.out.println("response : " + response[i]);
        		}*/
        		System.out.println("1 USD = " + res[1] + " " + currency);
        		
        		conversionMap.put(currency, res[1]);
			}
    		System.out.println("conversionMap : " + conversionMap);

    		/*
            while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
    		*/ 
    		/*
    		JSONArray resultArray = new JSONArray(inputLine);
    		for (int i=0; i < resultArray.length(); i++) {
    			JSONObject obj= (JSONObject)resultArray.get(i);
    			double rate = obj.getDouble("rate");
    			System.out.println("1 USD rate : " + rate);
			}
    		*/
    		in.close();
    		/*
    		URL googleQuote = new URL("http://www.google.com/ig/api?stock=532480");
    		//http://www.google.com/ig/api?stock=532480
    		URLConnection gq = googleQuote.openConnection();
    		BufferedReader br = new BufferedReader(new InputStreamReader(gq.getInputStream()));
    		String stockQuoteRes = br.readLine();
    		System.out.println("Stock Quote Response : " + stockQuoteRes); 
    		*/
    		
    	} catch (IOException io) {
    		System.err.println("There was error in connection the yahoo finance.");
    	}
    }
}
