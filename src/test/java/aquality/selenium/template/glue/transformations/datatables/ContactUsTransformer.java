package aquality.selenium.template.glue.transformations.datatables;

import aquality.selenium.template.models.ContactUsInfo;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

public class ContactUsTransformer implements TableTransformer<ContactUsInfo> {

    @Override
    public ContactUsInfo transform(DataTable dataTable) {
        ContactUsInfo contactUsInfoModel = new ContactUsInfo();
        contactUsInfoModel.setName(getValue(dataTable, "Name"));
        contactUsInfoModel.setCompany(getValue(dataTable, "Company"));
        contactUsInfoModel.setPhone(getValue(dataTable, "Phone"));
        contactUsInfoModel.setComment(getValue(dataTable, "Comment"));
        return contactUsInfoModel;
    }

    private String getValue(final DataTable dataTable, final String key) {
        return dataTable.cells().stream()
                .filter(cell -> cell.get(0).equals(key))
                .map(cell -> cell.get(1))
                .findFirst().orElse("");
    }
}
