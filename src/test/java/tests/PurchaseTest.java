package tests;

import core.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest extends BaseTest {

    @Test
    public void testPurchase() {
        // Открытие магазина
        driver.get(ConfigReader.getProperty("store.url"));

        // Выбор товара
        WebElement product = driver.findElement(By.id(ConfigReader.getProperty("product.id")));
        product.click();

        // Добавление товара в корзину
        WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(text(),'Добавить в корзину')]"));
        addToCartButton.click();

        // Переход к оплате
        WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Перейти к оплате')]"));
        checkoutButton.click();

        // Ввод платежных данных
        WebElement cardNumberInput = driver.findElement(By.id("cardNumber"));
        cardNumberInput.sendKeys(ConfigReader.getProperty("payment.cardNumber"));

        WebElement expiryDateInput = driver.findElement(By.id("expiryDate"));
        expiryDateInput.sendKeys(ConfigReader.getProperty("payment.expiryDate"));

        WebElement cvcInput = driver.findElement(By.id("cvc"));
        cvcInput.sendKeys(ConfigReader.getProperty("payment.cvc"));

        // Подтверждение покупки
        WebElement confirmButton = driver.findElement(By.xpath("//button[contains(text(),'Подтвердить покупку')]"));
        confirmButton.click();

        // Проверка успешного оформления заказа
        WebElement orderConfirmation = driver.findElement(By.xpath("//div[@class='order-confirmation']"));
        assertTrue(orderConfirmation.isDisplayed(), "Покупка не была оформлена!");
    }
}
