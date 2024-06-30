package com.praktika.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class NamePage extends BasePage{
    @AndroidFindBy(id = "ui_textEdit_Name")
    public WebElement NameField;
    @AndroidFindBy(id = "ui_button_Continue")
    public  WebElement ContinueButton;

    public NamePage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement GetNameHeader() {
        return GetElementWithText("What is your name?");
    }

    // We need to click to text field to focus it. Only after that we can send keys.
    public void FillNameAndContinue(String name) {
        NameField.click();
        NameField.sendKeys(name);
        ContinueButton.click();
    }
}
