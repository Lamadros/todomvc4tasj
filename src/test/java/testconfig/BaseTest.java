package testconfig;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

import static core.Concise.getDriver;
import static core.Concise.setDriver;

/**
 * Created by alex-macbook on 7/20/17.
 */
public class BaseTest {

    @Before
    public void setUp() {
        setDriver(new FirefoxDriver());
    }

    @After
    public void ThreadDown() {
        getDriver().quit();
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot(byte[] dataForScreenshot) {
        return dataForScreenshot;
    }
}
