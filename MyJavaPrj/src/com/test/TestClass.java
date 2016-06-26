package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class TestClass {

	public static void main(String[] args) {
		/*
		String value = "FaLse";
		boolean booleanValue = false;
		if(value != null) {
			booleanValue = Boolean.parseBoolean(value);
		}
		System.out.println("Boolean Value : " + booleanValue);,
		List<String> list = new ArrayList<String>();
		list.add("san");
		list.add("san");
		list.add("san");
		list.add("san");
		list.add("kum");
		list.add("san");
		list.add("san");
		list.add("mis");
		list.add("rag");
		System.out.println("List : " + list);
		Set<String> set = new LinkedHashSet<String>(list);
		System.out.println("Set : " + set);
		list = new ArrayList<String>(set);
		System.out.println("List : " + list);

		long startTime = System.currentTimeMillis();

		long total = 0;
		for (int i = 0; i < 10000000; i++) {
			for (int j = 0; j < 10000000; j++) {
				total += (i + j);
			}
			total += i;
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);

		String link = "http://";
		String imagePath = "/images/catalog/rado/ashford/46782346_FXA.jpg";
		String hostName = "www.ashford.com";
		String fullImagePath = link.concat(hostName.concat(imagePath));
		System.out.println("fullImagePath : " + fullImagePath);
		String imageURLPath = imagePath.substring(0,imagePath.lastIndexOf("/"));
		System.out.println("imageURLPath : " + imageURLPath);
		String imageName = fullImagePath.substring(fullImagePath.lastIndexOf("/") + 1);
		System.out.println("imageName : " + imageName);
		
		File mainFolder = new File("C:/Ashford/catalogimport");
		if (mainFolder.isDirectory()) {
			for (int i = 0; i < mainFolder.list().length; i++) {
				String fileName = mainFolder.list()[i];
				if (fileName.contains("updatedImages")) {
					System.out.println("File name : " + fileName);
					File imageFile = new File("C:/Ashford/catalogimport/"+fileName);
					System.out.println("File Absolute Path : " + imageFile.getAbsolutePath());
				}
			}
		}
		System.out.println("Value : " + 0%95);
		*/
		int loopCount = 10;
		int maxTryCount = 5;
		int tryCount = 0;
		boolean sleep = false;
		for(int i = 0; i < 10; i++) {
			System.out.println("I : " + i);
			while(true) {
				if(tryCount > maxTryCount) {
					sleep = true;
					break;
				}
				System.out.println("tryCount : " + tryCount);
				tryCount++;
			}
			tryCount = 0;
			if(sleep && i == 2) {
				break;
			}
		}
		
		final String BBBREGULARPRODUCTIONIMPORT = "BBBRegularProductionImport";
		String projectName = "BBBRegularProductionImport_2015-01-08[Jan 08,2015 15:06]";
		//String projectName = "BBBRegularProductionImport-(10006)[Jun 10,2013 08:00]";
		
		if (projectName.contains(BBBREGULARPRODUCTIONIMPORT.concat("_"))) {
			System.out.println("This is our import scheduler.");
		} else if (projectName.contains(BBBREGULARPRODUCTIONIMPORT.concat("-("))) {
			System.out.println("This is regular import scheduler.");	
		}
	}
}