package com.cybersecurity.io.fileconverter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.cybersecurity.io.interf.JSONXMLConverterl;


public class JSONXMLConverter implements JSONXMLConverterl {
	static String strobj = "";
	static FileWriter fileWriter =null;
	static Logger log = Logger.getLogger(JSONXMLConverter.class);
	@Override
	public void convertJSONtoXML(File jsonfile, File xmlfile) throws Exception {
		// TODO Auto-generated method stub
		Object refObj = new JSONParser().parse(new FileReader(jsonfile)); // To convert File to JSONObject

		if (refObj instanceof JSONObject) {   
			JSONObject jo = (JSONObject) refObj;
			if (jo != null) {
				strobj = "<object>\n";
				String jsonObject = toJSONObject(jo);

				strobj += jsonObject + "</object>";
			}
		} else if (refObj instanceof JSONArray) { 
			JSONArray jo = (JSONArray) refObj;
			strobj = "<array>";
			String retMap = toJSONArray(jo, null);
			strobj += retMap + "</array>";

		} else if (refObj instanceof String) { 
			strobj += "<string>\n" + refObj + "\n</string>\n";

		} else if (refObj instanceof Boolean) { 
			strobj += "<boolean>\n" + refObj + "\n</boolean>\n";

		} else {
			strobj += "<number>\n" + refObj + "\n</number>\n";	
		}
	
		try {
			fileWriter = new FileWriter(xmlfile);
			fileWriter.write(strobj);
			log.info("File written successfully...");
			fileWriter.close();
			log.info("File Closed...");
		} catch (Exception e) {
			log.error("Exception while writing JSON to XML file "+e.getMessage());
			e.printStackTrace();
		}finally {}
	}

	public static String toJSONObject(JSONObject jsonObj) throws Exception {
		
		String strObj1 = "";
		
		Iterator<String> Itr = jsonObj.keySet().iterator();
		while (Itr.hasNext()) {
			Object key = Itr.next();
			Object value = jsonObj.get(key);

			if (value instanceof JSONArray) {
				strObj1 += "<array name='" + key + "'>\n";

				value = toJSONArray((JSONArray) value, key);
				strObj1 +=value + "\n</array>";
			}

			else if (value instanceof JSONObject) {
				strObj1 += "<object name='" + key + "'>\n";

				value = toJSONObject((JSONObject) value);
				strObj1 += value + "\n</object>";

			} else if (value instanceof Boolean) {
				strObj1 += "<boolean name='" + key + "'>\n" + value + "\n</boolean>\n";

			} else if (value instanceof String) {
				strObj1 += "<string name='" + key + "'>\n" + value + "\n</string>\n";

			} else {
				strObj1 += "<number name='" + key + "'>\n" + value + "\n</number>\n";
			}

		}
		return strObj1;
	}

	public static String toJSONArray(JSONArray arrayObj, Object key) throws Exception {
		String strObj2 = "";
		
		
		for (int i = 0; i < arrayObj.size(); i++) {
			Object value = arrayObj.get(i);
			if (value instanceof JSONArray) {
				if (key != null) {
					strObj2 += "<array name='" + key + "'>\n";
				} else {
					strObj2 += "<array>";
				}
				value = toJSONArray((JSONArray) value, key);
				strObj2 += value + "\n</array>";
			}
			else if (value instanceof JSONObject) {
				strObj2 += "<object>\n";
				value = toJSONObject((JSONObject) value);
				strObj2 += value + "\n</object>\n";
			} else if (value instanceof Boolean) {
				strObj2 += "<boolean>\n" + value + "\n</boolean>";

			} else if (value instanceof String) {
				strObj2 += "<string>\n" + value + "\n</string>";

			} else {
				strObj2 += "<number>\n" + value + "\n</number>";
			}
		}
		return strObj2;
	}


}
