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

public class SelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.timeout = 15000;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @DisplayName("Переход в Issues и проверка наличия задачи AllureHomeWork")
    void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        // 1. Зайти на GitHub
        open("https://github.com/");

        // 2. Нажимаем на кнопку поиска
        $("button[aria-label^='Search']").click();

        // 3. Вводим название репозитория и нажимаем Enter
        $("input[placeholder^='Search']").setValue("Cherkasova-Mariia/HomeWorkAllureReports").pressEnter();

        // 4. Кликаем по ссылке на репозиторий (ждем, пока она появится, и кликаем)
        $$("a").findBy(text("HomeWorkAllureReports")).shouldBe(visible).click();

        // 5. Ищем ссылку с текстом "Issues" и кликаем по ней.
        // Метод $$("a") собирает все ссылки, findBy(text("Issues")) ищет нужную,
        // а shouldBe(visible) ждет, пока она прогрузится, чтобы не было ошибки.
        $$("a").findBy(text("Issues")).shouldBe(visible).click();

        // 6. Проверяем, что на странице есть задача AllureHomeWork
        $("body").shouldHave(text("AllureHomeWork"));
    }
}
