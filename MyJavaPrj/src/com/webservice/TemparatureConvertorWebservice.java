package com.webservice;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HTTPConstants;

import com.w3schools.www.webservices.CelsiusToFahrenheit;
import com.w3schools.www.webservices.CelsiusToFahrenheitResponse;
import com.w3schools.www.webservices.FahrenheitToCelsius;
import com.w3schools.www.webservices.TempConvertStub;

public class TemparatureConvertorWebservice {

	public static void main(String[] args) {

		try {

			String celsius = "37.7777"; 

			TempConvertStub convertStub = new TempConvertStub("http://www.w3schools.com/webservices/tempconvert.asmx?WSDL");
			convertStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(300000);
			convertStub._getServiceClient().getOptions().setProperty(HTTPConstants.CHUNKED,false);

			CelsiusToFahrenheit celsiusToFahrenheit = new CelsiusToFahrenheit();
			celsiusToFahrenheit.setCelsius(celsius);

			CelsiusToFahrenheitResponse celsiusToFahrenheitResponse = convertStub.CelsiusToFahrenheit(celsiusToFahrenheit);
			System.out.println(celsius + " Degree Celsius is " + celsiusToFahrenheitResponse.getCelsiusToFahrenheitResult() + " Degree Fahrenheit");
			
			FahrenheitToCelsius fahrenheitToCelsius = new FahrenheitToCelsius();
			fahrenheitToCelsius.setFahrenheit(celsiusToFahrenheit.getCelsius());
			
			System.out.println("Degree Celsius is " + fahrenheitToCelsius.getFahrenheit()+ " Degree Fahrenheit");

		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
