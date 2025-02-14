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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        long startTime = System.nanoTime(); // Начало замера времени

        // Открытие страницы логина
        driver.get(ConfigReader.getProperty("baseUrl"));
        long pageLoadTime = System.nanoTime();
        System.out.println("Время загрузки страницы авторизации: " + (pageLoadTime - startTime) / 1_000_000 + " мс");

        // Найти кнопку "Войти" и нажать
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Войти')]")));
        loginButton.click();

        // Ввести логин
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameInput.sendKeys(ConfigReader.getProperty("username"));

        // Ввести пароль
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordInput.sendKeys(ConfigReader.getProperty("password"));

        // Нажать кнопку "Войти"
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Войти')]")));
        submitButton.click();

        long loginProcessTime = System.nanoTime();
        System.out.println("Время ввода и подтверждения учетных данных: " + (loginProcessTime - pageLoadTime) / 1_000_000 + " мс");

        // Проверить, что авторизация успешна
        WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='user-profile']")));
        assertTrue(userProfile.isDisplayed(), "Пользователь не авторизован!");

        long endTime = System.nanoTime();
        System.out.println("Общее время авторизации: " + (endTime - startTime) / 1_000_000 + " мс");
    }
}
