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
import static io.qameta.allure.Allure.step;

public class StepsTest extends TestBase {

    @Test
    @DisplayName("Переход в Issues и проверка наличия задачи AllureHomeWork")
    void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open(TestData.BASEURL);
        });

        step("Ищем репозиторий " + TestData.REPOSITORY, () -> {
            $(Locators.SEARCH_BUTTON).click();
            $(Locators.SEARCH_REPOSITORY).setValue(TestData.REPOSITORY).pressEnter();
        });

        step("Кликаем по ссылке репозитория " + TestData.REPOSITORY, () -> {
            $$(Locators.ALL_LINKS).findBy(text(TestData.REPOSITORY_NAME)).shouldBe(visible).click();
        });

        step("Открываем таб " + TestData.ISSUES_TAB, () -> {
            $$(Locators.ALL_LINKS).findBy(text(TestData.ISSUES_TAB)).shouldBe(visible).click();
        });

        step("Проверяем наличие Issue с названием " + TestData.ISSUE, () -> {
            $(Locators.PAGE_BODY).shouldHave(text(TestData.ISSUE));
        });
    }

    @Test
    void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(TestData.REPOSITORY);
        steps.clickOnRepositoryLink(TestData.REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueName(TestData.ISSUE);
    }
}