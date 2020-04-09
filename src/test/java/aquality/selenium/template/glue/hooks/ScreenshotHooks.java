package aquality.selenium.template.glue.hooks;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScreenshotHooks {

    @After(order = 1)
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.embed(attachScreenshot(), "image/png");
        }
    }

    private byte[] attachScreenshot() {
        int scrollTimeout = 500;
        ShootingStrategy shootingStrategy = ShootingStrategies.viewportPasting(scrollTimeout);
        Screenshot fpScreenshot = new AShot()
                .shootingStrategy(shootingStrategy)
                .takeScreenshot(AqualityServices.getBrowser().getDriver());
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(fpScreenshot.getImage(), "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException ioe) {
            AqualityServices.getLogger().debug("IO Exception during preparing screenshot of full page%nException message", ioe);
            return new byte[] {};
        }
    }
}
