package aquality.selenium.template.cucumber.transformations;

import aquality.selenium.template.models.ContactUsInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableEntryTransformer;

import java.lang.reflect.Type;
import java.util.Map;

public class DataTableTypeTransformations {

    private final ObjectMapper mapper = new ObjectMapper();

    @DefaultDataTableEntryTransformer
    public ContactUsInfo getContactUsInfo(Map<String, String> entry, Type toValueType) {
        JavaType constructedType = mapper.constructType(toValueType);
        return mapper.convertValue(entry, constructedType);
    }
}
