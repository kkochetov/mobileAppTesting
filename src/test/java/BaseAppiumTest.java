import com.praktika.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

// This is a base test class. Every other test class should extend it.
public class BaseAppiumTest {

    protected AppiumDriver driver;

    // Initialization of appium driver.
    // Other possible initializations should be here as well. For example creating connection to DB.
    @BeforeClass
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    // Method to attach something to the report after each test.
    // For now, it only makes and attach to the report a screenshot if test fails.
    // I think, for frontend test screenshots is a must.
    // Here we can attach any other artefacts. For example, logs.
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            try {
                InputStream stream = new FileInputStream(screenshot);
                Allure.addAttachment("Screenshot", stream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Nothing special. Just closing the Appium driver and session on Appium server.
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
