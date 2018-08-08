package util;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassExtractor {
	
	public static List<String> getClassNamesFromPackage(String packageName) throws IOException, URISyntaxException{
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    URL packageURL;
	    List<String> names = new ArrayList<>();;

	    packageName = packageName.replace(".", "/");
	    packageURL = classLoader.getResource(packageName);

	    URI uri = new URI(packageURL.toString());
	    File folder = new File(uri.getPath());
        
        File[] contenuti = folder.listFiles();
        String entryName;
        for(File actual: contenuti){
            entryName = actual.getName();
            entryName = entryName.substring(0, entryName.lastIndexOf('.'));
            names.add(entryName);
        }
	    
	    return names;
	}
}
