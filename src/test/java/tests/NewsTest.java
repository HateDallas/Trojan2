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

public class NewsTest extends BaseTest {

    @Test
    public void testFindLastUpdate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        long startTime = System.nanoTime(); // Начало замера времени

        // Открытие раздела "Новости"
        WebElement newsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Новости')]")));
        newsLink.click();

        // Ожидание, что страница новостей загрузится
        WebElement newsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("news-section")));
        
        // Находим последнее обновление (предположим, что оно выделено как последнее сообщение)
        WebElement lastUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='news-item'][1]")));
        lastUpdate.click(); // Открываем последнее обновление

        long endTime = System.nanoTime(); // Конец замера времени
        long elapsedTime = endTime - startTime;
        System.out.println("Время нахождения последнего обновления: " + elapsedTime / 1_000_000 + " мс");

        // Проверка, что открылось нужное обновление
        WebElement updateContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("update-content")));
        assertTrue(updateContent.isDisplayed(), "Информация о последнем обновлении не найдена!");

        // Метрика: Ожидаемое количество кликов
        int clicks = 2; // 1 клик для открытия раздела, 1 клик для открытия обновления
        System.out.println("Количество кликов: " + clicks);

        // Оценка удобства навигации (по шкале 1-5, где 1 - неудобно, 5 - удобно)
        int navigationScore = 4; // Примерное значение
        System.out.println("Оценка удобства навигации: " + navigationScore);

        // Убедимся, что время нахождения обновления не превышает 10 секунд
        assertTrue(elapsedTime <= 10_000_000_000L, "Время нахождения информации превышает 10 секунд!");
    }
}
