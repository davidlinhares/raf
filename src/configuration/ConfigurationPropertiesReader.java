package configuration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationPropertiesReader {

	private static String result = "";
	private static InputStream inputStream;
	private static ConfigurationPropertiesReader configurationPropertiesReader = null;
	
	private ConfigurationPropertiesReader() {
		super();
	}
	
	public static synchronized ConfigurationPropertiesReader getInstance() {
		if(configurationPropertiesReader == null) {
			configurationPropertiesReader = new ConfigurationPropertiesReader();
		}
		return configurationPropertiesReader;
	}
	
	public final String getPropertiesValues(String propertyName) throws IOException {
		try {
			Properties properties = new Properties();
			String propertiesFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
			
			if(inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Properties file " + propertiesFileName + " not found!");
			}
			
			result = properties.getProperty(propertyName);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		
		return result;
	}
}
