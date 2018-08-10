package com.wf.configuration;

import java.io.IOException;
import java.util.HashMap;

public class ConfigurationReaderStatic {

	private static HashMap<String, String> dbConfig = new HashMap<>();
	
	public static HashMap<String, String> getDatabaseConfiguration() throws IOException {
		dbConfig.clear();
		dbConfig.put("database", ConfigurationPropertiesReader.getInstance().getPropertiesValues("database"));
		dbConfig.put("host", ConfigurationPropertiesReader.getInstance().getPropertiesValues("host"));
		dbConfig.put("port", ConfigurationPropertiesReader.getInstance().getPropertiesValues("port"));
		dbConfig.put("user", ConfigurationPropertiesReader.getInstance().getPropertiesValues("user"));
		dbConfig.put("password", ConfigurationPropertiesReader.getInstance().getPropertiesValues("password"));
		return dbConfig;
	}
}
