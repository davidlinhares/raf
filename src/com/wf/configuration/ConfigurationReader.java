package com.wf.configuration;
import java.io.IOException;
import java.util.HashMap;

public class ConfigurationReader {

	public static void main(String[] args) throws IOException {
		ConfigurationPropertiesReader properties = ConfigurationPropertiesReader.getInstance();
		HashMap<String, String> dbConfig = new HashMap<>();
		dbConfig.put("database", properties.getPropertiesValues("database"));
		dbConfig.put("user", properties.getPropertiesValues("user"));
		dbConfig.put("password", properties.getPropertiesValues("password"));
		dbConfig.put("host", properties.getPropertiesValues("host"));
		dbConfig.put("port", properties.getPropertiesValues("port"));
		System.out.println(dbConfig);
	}

}
