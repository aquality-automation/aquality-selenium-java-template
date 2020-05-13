package aquality.selenium.template.cucumber.transformations;

import aquality.selenium.template.models.ContactUsInfo;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTypeTransformations {

    @DataTableType
    public ContactUsInfo getContactUsInfo(Map<String, String> entry) {
        ContactUsInfo contactUsInfoModel = new ContactUsInfo();
        contactUsInfoModel.setName(entry.get("Name"));
        contactUsInfoModel.setCompany(entry.get("Company"));
        contactUsInfoModel.setPhone(entry.get("Phone"));
        contactUsInfoModel.setComment(entry.get("Comment"));
        return contactUsInfoModel;
    }
}
