package com.appsoftcorp.automation.factories;

import com.codeborne.selenide.Configuration;
import com.appsoftcorp.framework.base.BasePage;
import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class PageFactory {

    public static <T extends BasePage> T getPage(Class<T> basePageClass) {
        String browser = Configuration.browser;
        try {
            return (T) Class.forName(getBrowserSpecificPageClassName(basePageClass, browser)).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create page instance", e);
        }
    }

    @NotNull
    private static <T extends BasePage> String getBrowserSpecificPageClassName(Class<T> basePageClass, String browser) {
        String baseClassText = "Base";
        return String.format("%s.%s", basePageClass.getPackage().getName(),
                basePageClass.getSimpleName().replace(baseClassText, StringUtils.capitalize(browser)));
    }
}
