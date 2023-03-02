package aquality.selenium.template.utilities;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

@UtilityClass
public class FileHelper {
    private static final String RESOURCES_PATH = "/src/test/resources/";
    private static final String JAVASCRIPT_PATH = "/javascript/";

    private static String getProjectBaseDir(){
        return System.getProperty("user.dir") != null ? System.getProperty("user.dir") : System.getProperty("project.basedir");
    }

    public static File getResourceFileByName(String fileName) {
        return Paths.get(getProjectBaseDir(), RESOURCES_PATH + fileName).toFile();
    }

    @SneakyThrows
    public static File getJavascriptFileByName(String fileNameWithoutExtension) {
        return new File(Objects.requireNonNull(
                FileHelper.class.getResource(JAVASCRIPT_PATH + fileNameWithoutExtension + ".js")).toURI());
    }
}
