package aquality.selenium.template.testng.utilities;

import aquality.selenium.template.modules.CustomBrowserModule;
import aquality.selenium.template.modules.ServiceModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.testng.IModuleFactory;
import org.testng.ITestContext;

public class ModuleFactory implements IModuleFactory {
    @Override
    public Module createModule(ITestContext iTestContext, Class<?> aClass) {
        return Modules.combine(new CustomBrowserModule(), new ServiceModule());
    }
}
