package com.praktika.pages.elements;

import com.praktika.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SwitchLanguageNotification extends BasePage {
    String selectedLanguage;

    public SwitchLanguageNotification(AppiumDriver driver, String selectedLanguage) {
        super(driver);
        this.selectedLanguage = selectedLanguage;
    }

    // There is no so many buttons on this page, so we can get the buttons by it`s order.
    @AndroidFindBy(xpath = "//android.widget.Button[1]")
    public  WebElement switchToButton;

    @AndroidFindBy(xpath = "//android.widget.Button[2]")
    public  WebElement KeepEnglishButton;

    public WebElement GetSwitchLanguageText() {
        return GetElementWithText(String.format("You've chosen %s", selectedLanguage));
    }

    public void Switch() {
        switchToButton.click();
    }

    public void  KeepEnglish() {
        KeepEnglishButton.click();
    }

}
