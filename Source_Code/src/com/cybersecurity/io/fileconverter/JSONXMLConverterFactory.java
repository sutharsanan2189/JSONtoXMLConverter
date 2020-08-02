package com.cybersecurity.io.fileconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.cybersecurity.io.main.JSONXMLConverterMain;

/*
 * 
 * Created by Sutharsanan Chinnaiyan
 * Created on 31 July 2020
 * 
 * Abstract class is used to hide the implementation of method and member variables
 */
public abstract class JSONXMLConverterFactory  {
	 final static File json = new  File("D:\\files\\json\\json_file.txt"); // Input file 
	 final static File xml = new  File("D:\\files\\xml\\xml_file.txt"); //Output file
	 static Logger log = Logger.getLogger(JSONXMLConverterFactory.class);
	 	//non - abstract method 
	    public void write() throws Exception {
	        try
	        {
	        	JSONXMLConverter objxml = new JSONXMLConverter();
	        	objxml.convertJSONtoXML(json, xml);
	        }catch (FileNotFoundException fe) {
	        	log.error("File Not Found at "+fe.getMessage());
	        }catch (Exception e) {
	        	log.error("Exception "+e.getMessage());
	        }
	    }
}
