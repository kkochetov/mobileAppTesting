package com.praktika.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

// Android notification popup as a separate class page.
public class AndroidNotificationPage extends BasePage{
    public AndroidNotificationPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.android.permissioncontroller:id/grant_dialog")
    public WebElement AndroidGrandDialog;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    public  WebElement GrandButton;

    public WebElement getGrandDialog() {
        return WaitForElementExists(AndroidGrandDialog);
    }

    public void GrandAskedPermission() {
        GrandButton.click();
    }
}
