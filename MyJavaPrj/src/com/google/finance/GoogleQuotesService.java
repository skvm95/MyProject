package com.google.finance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GoogleQuotesService {

	public static void main(String[] args) {
		try {
			URL googleQuote = new URL("http://www.google.com/ig/api?stock=532480");
			//http://www.google.com/ig/api?stock=532480
			//http://finance.google.com/finance/info?client=ig&q=BOM:532480
			//http://finance.google.com/finance/info?client=ig&q=BOM%3a532480
			URLConnection gq = googleQuote.openConnection();
			//System.out.println("Ïnput Stream : " + gq.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(gq.getInputStream()));
			String stockQuoteRes = br.readLine();
			System.out.println("Stock Quote Response : " + stockQuoteRes);
			printStockDetails(stockQuoteRes, getStockQuoteDetailForTag());
			br.close();
		} catch (IOException io) {
			System.err.println("There was error in connection the google finance.");
		}
	}
	
	private static ArrayList<String> getStockQuoteDetailForTag() {
		String startingChar = "<";
		String endingChar = "=";
		ArrayList<String> stockQuoteTagList = new ArrayList<String>();
		stockQuoteTagList.add(startingChar + "company data" + endingChar);
		stockQuoteTagList.add(startingChar + "last data" + endingChar);
		stockQuoteTagList.add(startingChar + "high data" + endingChar);
		stockQuoteTagList.add(startingChar + "low data" + endingChar);
		stockQuoteTagList.add(startingChar + "change data" + endingChar);
		stockQuoteTagList.add(startingChar + "perc_change data" + endingChar);
		stockQuoteTagList.add(startingChar + "volume data" + endingChar);
		return stockQuoteTagList;
	}
	
	public static void printStockDetails(String pStockQuoteResponse, ArrayList<String> pStockQuoteDetailForTag) {
		for (String stockQuoteTag : pStockQuoteDetailForTag) {
			int index = pStockQuoteResponse.indexOf(stockQuoteTag) + stockQuoteTag.length() + 1;
			int index2 = pStockQuoteResponse.indexOf('"', index);
			String stockQuote = pStockQuoteResponse.substring(index, index2);
			System.out.println(stockQuoteTag + ">: " + stockQuote);
		}
	}
}