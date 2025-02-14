package tests;

import core.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class CreateForumPostTest extends BaseTest {

    @Test
    public void testCreateForumPost() {
        // Переход на форум
        driver.get(ConfigReader.getProperty("forum.url"));

        // Выбор раздела форума
        WebElement forumSection = driver.findElement(By.xpath("//a[contains(text(), '" + ConfigReader.getProperty("forum.section") + "')]"));
        forumSection.click();

        // Нажатие кнопки "Создать тему"
        WebElement createTopicButton = driver.findElement(By.xpath("//button[contains(text(), 'Создать тему')]"));
        createTopicButton.click();

        // Ввод заголовка
        WebElement titleInput = driver.findElement(By.id("topicTitle"));
        titleInput.sendKeys(ConfigReader.getProperty("forum.topicTitle"));

        // Ввод текста
        WebElement contentInput = driver.findElement(By.id("topicContent"));
        contentInput.sendKeys(ConfigReader.getProperty("forum.topicContent"));

        // Отправка темы
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'Отправить')]"));
        submitButton.click();

        // Проверка, что тема успешно создана и отображается в списке
        WebElement newTopic = driver.findElement(By.xpath("//h2[contains(text(), '" + ConfigReader.getProperty("forum.topicTitle") + "')]"));
        assertTrue(newTopic.isDisplayed(), "Тема не была создана!");
    }
}
