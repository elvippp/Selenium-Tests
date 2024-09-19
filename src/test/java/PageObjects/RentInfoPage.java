package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentInfoPage {
    private WebDriver driver;
    private By calendarPlaceholder = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By calendarObject = By.className("react-datepicker");
    private By orderDurationPlaceholder =By.className("Dropdown-placeholder");
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By confirmationWindow = By.className("Order_Modal__YZ-d3");
    private By yesButton = By.xpath(".//button[text()='Да']");

    /**
     * Конструктор
     * @param driver Веб-драйвер
     */
    public RentInfoPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Заполнение формы заказа самоката
     * @param orderDate Когда привезти самокат
     * @param orderDuration Срок аренды
     */
    public void feelOrderForm(String orderDate, String orderDuration) {
        driver.findElement(calendarPlaceholder).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarObject));
        driver.findElement(By.xpath("//div[@aria-label='" + orderDate + "']")).click();
        driver.findElement(orderDurationPlaceholder).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderDurationPlaceholder));

        driver.findElement(By.xpath(".//div[text()='" + orderDuration + "']")).click();

        driver.findElement(orderButton).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(confirmationWindow));
        driver.findElement(yesButton).click();
    }

}
