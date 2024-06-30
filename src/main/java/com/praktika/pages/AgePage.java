package com.praktika.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AgePage extends BasePage{
    //Make it separate because it is not age range
    @AndroidFindBy(id = "ui_button_unknown")
    public  WebElement NotSayButton;

    public AgePage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement GetAgeHeader() {
        return GetElementWithText("How old are you?");
    }

    public void SelectAge(Ages age) {
        WebElement ageButton = driver.findElement(By.id(String.format("ui_button_%s", age.toString())));
        ageButton.click();
    };

    //Ages enum as for genders
    public enum Ages{
        ageUnder18,
        age18_24,
        age25_34,
        age35_44,
        age45_54,
        age55_64,
        ageOver65,
    }
}
