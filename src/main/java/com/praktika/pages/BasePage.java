package com.praktika.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Base page class with helpers methods.
public class BasePage {
    private final int TIMEOUT = 30;
    private final WebDriverWait wait;
    protected AppiumDriver driver;

    // Initializing page with searching all @AndroidFindBy like annotations. and making own waiter for every page.
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    // Most used method to find element containing text.
    // Sometime (when we do not know id for example) we can search element by text. I don`t label that method as completely wrong.
    // Human users are finding elements by text, and we're emulating user behaviour.
    public WebElement GetElementWithText(String text) {
        String xPath = String.format("//*[contains(@content-desc,\"%s\")]", text);
        return WaitForElementExists(By.xpath(xPath));
    }

    // Simple search by Id
    protected WebElement GetElementById(String id) {
        return driver.findElement(By.id(id));
    }

    //Next two methods is just wait with timeout for element visible or exists

    protected WebElement WaitForElementExists(WebElement element) {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element;
        }

    protected WebElement WaitForElementExists(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }



}
