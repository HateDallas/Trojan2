package tests;

import core.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void testUserLogin() {
        // Увеличиваем время ожидания до 60 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Найти кнопку "Войти" и нажать
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Войти')]")));
        loginButton.click();

        // Ввести логин
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))); // Укажи правильный ID
        usernameInput.sendKeys(ConfigReader.getProperty("username"));

        // Ввести пароль
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))); // Укажи правильный ID
        passwordInput.sendKeys(ConfigReader.getProperty("password"));

        // Нажать кнопку "Войти"
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Войти')]")));
        submitButton.click();

        // Проверить, что авторизация успешна
        WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='user-profile']"))); // Проверь правильный XPath
        assertTrue(userProfile.isDisplayed(), "Пользователь не авторизован!");
    }
}
