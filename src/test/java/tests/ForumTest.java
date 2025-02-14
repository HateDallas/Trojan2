import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class ForumTest {

    @Test
    public void testOpenForum() {
        // Настраиваем WebDriver для Firefox
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        try {
            // Открываем веб-сайт
            driver.get("https://rf4game.ru/forum/index.php?/forum/5-технические-вопросы/");

            // Ожидание загрузки кнопки "Форум"
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Форум")));

            // Нажимаем на кнопку "Форум"
            forumButton.click();

            // Проверяем, что заголовок страницы содержит "Форум"
            Assertions.assertTrue(driver.getTitle().contains("Форум"),
                    "Форум не открылся, заголовок страницы не соответствует ожидаемому");

        } finally {
            // Закрываем браузер
            driver.quit();
        }
    }
}
