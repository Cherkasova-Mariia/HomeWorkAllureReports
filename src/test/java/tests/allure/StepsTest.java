package tests.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class StepsTest {

    @BeforeAll
    static void setUp() {
        Configuration.timeout = 15000;
        Configuration.browserSize = "1920x1080";
    }
private static final String REPOSITORY = "Cherkasova-Mariia/HomeWorkAllureReports";
private static final String ISSUE = "AllureHomeWork";

    @Test
    @DisplayName("Переход в Issues и проверка наличия задачи AllureHomeWork")
    void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
                    open("https://github.com");
                });
        step("Ищем репозиторий " + REPOSITORY, () -> {
        $("button[aria-label^='Search']").click();
        $("input[placeholder^='Search']").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $$("a").findBy(text("HomeWorkAllureReports")).shouldBe(visible).click();
        });
        step("Открываем таб Issues", () -> {
            $$("a").findBy(text("Issues")).shouldBe(visible).click();
        });
        step("Проверяем наличие Issue с названием " + ISSUE, () -> {
        $("body").shouldHave(text(ISSUE));
         });
    }

    @Test
    void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueName(ISSUE);
    }
}
