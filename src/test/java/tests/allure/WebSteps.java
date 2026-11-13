package tests.allure;

import com.codeborne.selenide.WebDriverRunner;
import helpers.Locators;
import helpers.TestData;
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
        open(TestData.BASEURL);
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(Locators.SEARCH_BUTTON).click();
        $(Locators.SEARCH_REPOSITORY).setValue(repo).pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $$(Locators.ALL_LINKS).findBy(text(TestData.REPOSITORY_NAME)).shouldBe(visible).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $$(Locators.ALL_LINKS).findBy(text(TestData.ISSUES_TAB)).shouldBe(visible).click();
    }

    @Step("Проверяем наличие Issue с названием {TestData.ISSUE}")
    public void shouldSeeIssueName(String issue) {
        $(Locators.PAGE_BODY).shouldHave(text(TestData.ISSUE));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}