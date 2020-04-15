package aquality.selenium.template.cucumber.transformations;

import aquality.selenium.template.cucumber.transformations.datatables.ContactUsTransformer;
import aquality.selenium.template.models.ContactUsInfo;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(ContactUsInfo.class, new ContactUsTransformer()));
    }
}
