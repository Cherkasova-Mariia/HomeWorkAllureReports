package tests.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("Cherkasova_Mariia")
@Feature("Issue в репозитории")
public class LabelsTest {

    @Test
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    void testStaticLabels() {
    }

    @Test
    @DisplayName("Динамические метки")
    void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание Issue для авторизованного пользователя")
        );

        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "Cherkasova_Mariia");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("Testing", "https://testing.github.com");
    }
}