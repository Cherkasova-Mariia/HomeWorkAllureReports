package tests.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Locators;
import helpers.TestBase;
import helpers.TestData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest extends TestBase {

    @Test
    @DisplayName("Переход в Issues и проверка наличия задачи AllureHomeWork")
    void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(TestData.BASEURL);

        $(Locators.SEARCH_BUTTON).click();
        $(Locators.SEARCH_REPOSITORY).setValue(TestData.REPOSITORY).pressEnter();
        $$(Locators.ALL_LINKS).findBy(text(TestData.REPOSITORY_NAME)).shouldBe(visible).click();
        $$(Locators.ALL_LINKS).findBy(text(TestData.ISSUES_TAB)).shouldBe(visible).click();

        $(Locators.PAGE_BODY).shouldHave(text(TestData.ISSUE));
    }
}