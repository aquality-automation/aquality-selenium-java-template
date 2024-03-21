package aquality.selenium.template.testng.utilities;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.template.models.ContactUsInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@UtilityClass
public class JsonDataProvider {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @DataProvider
    @SneakyThrows
    public Object[][] getContacts() {
        return getDataListFromJson("contacts", ContactUsInfo[].class);
    }

    private Object[][] getDataListFromJson(String filename, Class<? extends Object[]> valueType) {
        Object[] dataList;
        try {
            File file = Paths.get(String.format("src/test/resources/data/%s.json", filename)).toFile();
            dataList = MAPPER.readValue(file, valueType);
        }
        catch (IOException exception) {
            AqualityServices.getLogger().debug("IO error occurred while reading the data from the file " + filename, exception);
            throw new RuntimeException(exception);
        }
        return new Object[][] {dataList};
    }
}
