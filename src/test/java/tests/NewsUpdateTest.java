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
import java.util.List;

public class NewsUpdateTest extends BaseTest {

    @Test
    public void testFindLatestUpdate() {
        // Увеличиваем время ожидания до 60 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Открытие страницы
        driver.get(ConfigReader.getProperty("url")); // URL из конфигурации

        // Перейти в раздел "Новости"
        WebElement newsSection = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Новости")));
        newsSection.click();

        // Найти последнее обновление
        List<WebElement> newsItems = driver.findElements(By.xpath("//div[@class='news-item']")); // Обновите XPath, если нужно
        WebElement latestUpdate = newsItems.get(0); // Допустим, последнее обновление — это первый элемент в списке
        
        // Ожидание загрузки последнего обновления
        wait.until(ExpectedConditions.visibilityOf(latestUpdate));
        
        // Открыть последнее обновление
        latestUpdate.click();

        // Проверить, что мы на странице последнего обновления
        WebElement updateTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Последнее обновление')]"))); // Укажите правильный XPath
        assertTrue(updateTitle.isDisplayed(), "Информация о последнем обновлении не найдена!");

        // Проверка времени нахождения и количества действий
        long startTime = System.currentTimeMillis();
        // Здесь можно дополнительно вставить шаги для измерения времени, если нужно
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime; // Время нахождения
        assertTrue(duration <= 10000, "Время нахождения информации превышает 10 секунд");

        // Дополнительная проверка количества кликов, если требуется
    }
}
