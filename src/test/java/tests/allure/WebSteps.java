package tests.allure;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $("button[aria-label^='Search']").click();
        $("input[placeholder^='Search']").setValue(repo).pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        // Ищем ссылку по точному href (надежнее, чем текст)
        $("a[href='/Cherkasova-Mariia/HomeWorkAllureReports']").shouldBe(visible).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $$("a").findBy(text("Issues")).shouldBe(visible).click();
    }

    @Step("Проверяем наличие Issue с названием {issue}")
    public void shouldSeeIssueName(String issue) {
        // ИСПРАВЛЕНО: ищем ссылку на задачу, а не весь body
        $("a[href*='/issues/']").shouldHave(text(issue));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}