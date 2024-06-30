import com.praktika.pages.*;
import com.praktika.pages.elements.SwitchLanguageNotification;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

// Class with tests. Extends Base test class.
public class OnboardingProcessTest extends BaseAppiumTest{

    // Method with test from a task description
    // Here, using a Page object model I create a page object and use it methods with encapsulated logic.
    // I understand that this test looks like spaghetti a little, but this is truly end-to-end test which is testing
    // a user scenario. And user scenarios might be long. For cases like this we can use instruments like mentioned below Allure steps.
    // The other way to separate test code to methods and call these methods from @Test.
    // Also, I don`t want to make a separate tests for every page and make it dependable on each other.
    // But it is another way to rearrange the code of course.
    @Test
    public void OnboardingTest() {

        // Allure.step is only to arrange parts of test in a report. With is, report looks as a human-readable test-case.
        // Pseudo BDD style :-)
        Allure.step("Go to start page", s -> {
            StartPage startPage = new StartPage(driver);
            // TestNg asserts. Ensuring the element should not only exist (this check made when appium tries to get element)
            // but also visible.
            Assert.assertTrue(startPage.LoginLink.isDisplayed(), "Start page opened");
            startPage.Start();
        });

        Allure.step("Grand notification permissions", s-> {
            AndroidNotificationPage androidNotificationPage = new AndroidNotificationPage(driver);
            Assert.assertTrue(androidNotificationPage.getGrandDialog().isDisplayed(), "Notification permission dialog opened");
            // Methods with encapsulated logic should make tests a readable and easier to understand.
            androidNotificationPage.GrandAskedPermission();
        });

        Allure.step("Select male gender", s -> {
            GenderPage genderPage = new GenderPage(driver);
            Assert.assertTrue(genderPage.GetGenderHeader().isDisplayed(), "Gender selection dialog opened");
            genderPage.SelectGender(GenderPage.Genders.man);
        });

        Allure.step("Select age 35 - 44 years old", s -> {
            AgePage agePage = new AgePage(driver);
            Assert.assertTrue(agePage.GetAgeHeader().isDisplayed(), "Age selection dialog opened");
            agePage.SelectAge(AgePage.Ages.age35_44);
        });

        Allure.step("Fill name", s -> {
            NamePage namePage = new NamePage(driver);
            Assert.assertTrue(namePage.GetNameHeader().isDisplayed(), "Name dialog opened");
            namePage.FillNameAndContinue("Name Jonson");
        });


        Allure.step("Search Spanish language and switch to it", s -> {
            LanguagePage languagePage = new LanguagePage(driver);
            Assert.assertTrue(languagePage.GetLanguageHeader().isDisplayed(), "Name dialog opened");
            languagePage.Search("Sp");
            languagePage.SelectLanguage("Spanish");

            SwitchLanguageNotification switchLanguageNotification = languagePage.SwitchLanguageNotification();
            Assert.assertTrue(switchLanguageNotification.GetSwitchLanguageText().isDisplayed(), "Switch language dialog opened");
            switchLanguageNotification.Switch();
        });


        Allure.step("Check that our interface on Spanish now", s -> {
            // For the last check I do not use a separate page but Base page because according to task description
            // I should stop after language changing.
            BasePage page = new BasePage(driver);
            Assert.assertTrue(page.GetElementWithText("¡Hola!").isDisplayed(), "Can find Spanish interface");
        });
    }
}
