package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    private final WebDriver driver;
    private final By faqList = By.className("accordion__button");
    private final By orderButton = By.className("Button_Button__ra12g");
    private final By cookieButton = By.className("App_CookieButton__3cvqF");
    private final By orderButtonLower = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    /**
     * Конструктор
     * @param driver Веб-драйвер
     */
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Прокрутка старницы к элементу
     * @param element веб-элемент
     */
    private void scrollToFaqListToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Получить список элементов FAQ
     * @return Список элементов
     */
    private List<WebElement> getListElements() {
        return driver.findElements(faqList);
    }

    /**
     * Прокликать весь список FAQ
     */
    public void clickOnFaqListElements(){
        List<WebElement> elements = getListElements();
        for (WebElement element : elements) {
            scrollToFaqListToElement(element);
            element.click();
        }
    }

    /**
     * Нажать на кнопку Заказать
     */
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    /**
     * Нажать на кнопку принять куки
     */
    public void clickCookieButton(){
        driver.findElement(cookieButton).click();
    }

    /**
     * Нажать на нижнюю кнопку Заказать
     */
    public void clickOrderButtonLower() {
        var orderBottomButton = driver.findElement(orderButtonLower);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderBottomButton);
        orderBottomButton.click();
    }
}
