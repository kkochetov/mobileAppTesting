package com.praktika.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class GenderPage extends BasePage{
    public GenderPage(AppiumDriver driver) {
        super(driver);
    }

    // Using these elements with text to ensure that we on the right page.
    public WebElement GetGenderHeader() {
        return GetElementWithText("Welcome to Praktika!");
    }

    public void SelectGender(Genders gender) {
        WebElement genderButton = GetElementById(String.format("ui_button_%s", gender));
        genderButton.click();
    }

    // Gender enum to avoid duplicating code for every gender button. If there is a new gender be added in the app,
    // we just need to add it to enum
    public enum Genders {
        man,
        woman,
        unknown
    }
}
