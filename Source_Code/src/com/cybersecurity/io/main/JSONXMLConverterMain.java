package com.cybersecurity.io.main;

import org.apache.log4j.Logger;

import com.cybersecurity.io.fileconverter.JSONXMLConverterFactory;
/*
 * Created By Sutharsanan Chinnaiyan
 * Created On 31 Jul 2020
 * JSON to XML Converter Factory Main Class
 * 
 */

public class JSONXMLConverterMain extends JSONXMLConverterFactory 
{
	static Logger log = Logger.getLogger(JSONXMLConverterMain.class);
	public static void main(String[] args) throws Exception {
		JSONXMLConverterMain obj = new JSONXMLConverterMain();
		obj.write();
	}
}
