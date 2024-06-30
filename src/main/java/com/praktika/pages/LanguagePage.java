package com.praktika.pages;

import com.praktika.pages.elements.SwitchLanguageNotification;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LanguagePage extends BasePage{
    @AndroidFindBy(id = "ui_textEdit_Search")
    public WebElement SearchField;

    // Save selected language for using it in notification element
    String selectedLanguage;

    public LanguagePage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement GetLanguageHeader() {
        return GetElementWithText("What is your\nnative language?");
    }

    // Find and click language button
    public void SelectLanguage(String Language) {
        WebElement languageButton = GetElementById(String.format("ui_button_%s", Language));
        languageButton.click();
        selectedLanguage = Language;
    }

    // Switch language notification logically is a part or this language page. But in code it is a page object.
    public SwitchLanguageNotification SwitchLanguageNotification() {
        return new SwitchLanguageNotification(driver, selectedLanguage);
    }

    public void Search(String query) {
        SearchField.click();
        SearchField.sendKeys(query);
    }

}
