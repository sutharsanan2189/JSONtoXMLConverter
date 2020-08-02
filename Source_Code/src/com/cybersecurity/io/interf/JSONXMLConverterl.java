package com.cybersecurity.io.interf;

import java.io.File;

/*
 * Created By Sutharsanan Chinnaiyan
 * Created On 31 Jul 2020
 * 
 * Description: Functional Interface JDK 1.8
 * 
 */

@FunctionalInterface
public interface JSONXMLConverterl {
	public void convertJSONtoXML(File json,File xml) throws Exception;
}
