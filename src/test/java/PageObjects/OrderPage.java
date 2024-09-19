package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final By namePlaceholder = By.xpath("//input[@placeholder='* Имя']");
    private final By fullNamePlaceholder = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressPlaceholder = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By numderPlaceholder = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By indergroundPlaceholder = By.xpath("//input[@placeholder='* Станция метро']");
    private final By undergroundList = By.className("select-search__select");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    /**
     * Конструктор
     * @param driver Веб-драйвер
     */
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Заполнение формы заказа самоката
     * @param undergroundName Метро
     * @param userName Имя
     * @param fullName Фамилия
     * @param address Адрес
     * @param phoneNumber Номер телефона
     */
    public void feelOrderForm(String userName, String fullName, String address, String phoneNumber, String undergroundName){
        driver.findElement(namePlaceholder).sendKeys(userName);
        driver.findElement(fullNamePlaceholder).sendKeys(fullName);
        driver.findElement(addressPlaceholder).sendKeys(address);
        driver.findElement(numderPlaceholder).sendKeys(phoneNumber);
        driver.findElement(indergroundPlaceholder).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(undergroundList));

        driver.findElement(By.xpath(".//*[text()='"+undergroundName+"']")).click();
    }

    /**
     * Нажать на кнопку Далее
     */
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
}
