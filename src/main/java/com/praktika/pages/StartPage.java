package com.praktika.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class StartPage extends BasePage{
    public StartPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "ui_button_GetStarted")
    public WebElement GetStartedButton;

    @AndroidFindBy(id = "ui_button_Login")
    public  WebElement LoginLink;

    public void Start() {
        GetStartedButton.click();
    }

}
