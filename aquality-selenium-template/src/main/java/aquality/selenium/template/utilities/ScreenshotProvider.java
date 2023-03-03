package aquality.selenium.template.utilities;

import java.io.IOException;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.getLogger;
import static aquality.selenium.template.utilities.FileHelper.getJavascriptFileByName;

public class ScreenshotProvider implements IScreenshotProvider {

    public byte[] takeScreenshot() {
        try {
            int fullWidth = (int)(long) getBrowser().executeScript(getJavascriptFileByName("getScrollHeight"));
            int fullHeight = (int)(long) getBrowser().executeScript(getJavascriptFileByName("getScrollHeight"));
            getBrowser().setWindowSize(fullWidth, fullHeight);
            return getBrowser().getScreenshot();
        }
        catch (IOException exception) {
            getLogger().fatal("IO Exception during preparing screenshot of full page", exception);
            return new byte[0];
        }
    }
}
