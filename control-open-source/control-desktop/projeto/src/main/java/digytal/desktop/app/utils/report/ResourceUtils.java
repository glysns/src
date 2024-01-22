package digytal.desktop.app.utils.report;


import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class ResourceUtils {
    public static InputStream getAsStream(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
    }

    public static File getAsFile(String resourceName) throws Exception {
        URL resource  = getAsURL(resourceName);
        return new File(resource.toURI());
    }
    public static String getAsString(String resourceName) throws Exception {
        return getAsURL(resourceName).getPath();
    }
    public static URL getAsURL(String resourceName) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(resourceName);
		return resource;
    }
}
