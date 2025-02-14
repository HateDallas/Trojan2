package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PurchaseTest {

    @Test
    public void testPurchaseItem() {
        // Устанавливаем путь до драйвера (например, ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver");

        // Создаем экземпляр WebDriver
        WebDriver driver = new ChromeDriver();

        long startTime = System.currentTimeMillis(); // Начало замера времени

        try {
            // Шаг 1: Открываем веб-сайт
            driver.get("https://rf4game.ru/"); // Замените на адрес сайта

            // Шаг 2: Переходим в магазин
            WebElement shopButton = driver.findElement(By.id("shop-button"));
            shopButton.click();

            // Шаг 3: Выбираем предмет
            WebElement item = driver.findElement(By.cssSelector(".item-selector"));
            item.click();

            // Шаг 4: Добавляем в корзину
            WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
            addToCartButton.click();

            // Шаг 5: Переходим к оплате
            WebElement checkoutButton = driver.findElement(By.id("checkout"));
            checkoutButton.click();

            // Шаг 6: Вводим платежные данные
            WebElement cardNumberInput = driver.findElement(By.id("card-number"));
            WebElement cardExpirationInput = driver.findElement(By.id("card-expiration"));
            WebElement cardCVVInput = driver.findElement(By.id("card-cvv"));
            
            cardNumberInput.sendKeys("4111111111111111");
            cardExpirationInput.sendKeys("12/24");
            cardCVVInput.sendKeys("123");

            // Шаг 7: Подтверждаем покупку
            WebElement confirmButton = driver.findElement(By.id("confirm-purchase"));
            confirmButton.click();

            // Шаг 8: Проверяем успешность покупки
            WebElement purchaseConfirmation = driver.findElement(By.id("purchase-confirmation"));
            Assert.assertTrue("Покупка не была успешно завершена", purchaseConfirmation.isDisplayed());

            // Ожидаемый результат: Покупка успешно оформлена
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Произошла ошибка при оформлении покупки: " + e.getMessage());
        } finally {
            // Закрываем браузер
            driver.quit();
        }

        long endTime = System.currentTimeMillis(); // Завершение замера времени
        long elapsedTime = endTime - startTime; // Время оформления заказа
        System.out.println("Время оформления заказа: " + elapsedTime + " миллисекунд");

        // Время оформления заказа не должно превышать 30 секунд
        Assert.assertTrue("Время оформления заказа превышает 30 секунд", elapsedTime <= 30000);
    }
}
