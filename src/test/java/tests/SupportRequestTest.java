package tests;

import core.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class SupportRequestTest extends BaseTest {

    @Test
    public void testCreateSupportRequest() {
        // Переход на страницу поддержки
        driver.get(ConfigReader.getProperty("support.url"));

        // Нажатие кнопки "Создать обращение"
        WebElement createRequestButton = driver.findElement(By.xpath("//button[contains(text(), 'Создать обращение')]"));
        createRequestButton.click();

        // Заполнение формы обращения
        WebElement subjectInput = driver.findElement(By.id("subject"));
        subjectInput.sendKeys(ConfigReader.getProperty("support.subject"));

        WebElement messageInput = driver.findElement(By.id("message"));
        messageInput.sendKeys(ConfigReader.getProperty("support.message"));

        // Отправка обращения
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Отправить запрос')]"));
        submitButton.click();

        // Проверка, что уведомление о создании обращения отображается
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='confirmation-message']"));
        assertTrue(confirmationMessage.isDisplayed(), "Запрос не был отправлен!");
    }
}
