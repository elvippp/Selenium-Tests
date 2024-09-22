package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * POM страницы заказа информации по Аренде самоката
 */
public class RentInfoPage {

    /**
     * Веб-драйвер
     */
    private final WebDriver driver;

    //region Локаторы для страницы аренды
    private final By calendarPlaceholder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By calendarObject = By.className("react-datepicker");
    private final By orderDurationPlaceholder =By.className("Dropdown-placeholder");
    private final By blackColorCheckbox = By.xpath(".//input[@id='black']");
    private final By greyColorCheckbox = By.xpath(".//input[@id='grey']");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmationWindow = By.className("Order_Modal__YZ-d3");
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //endregion

    /**
     * Локатор для всплывающего окна об успешном создании заказа
     */
    private final By modalWindow = By.className("Order_Modal__YZ-d3");

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
     * @param color Цвет самоката
     * @param comment Комментарии
     */
    public void feelOrderForm(String orderDate, String orderDuration, String color, String comment) {
        driver.findElement(calendarPlaceholder).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarObject));
        driver.findElement(By.xpath("//div[@aria-label='" + orderDate + "']")).click();
        driver.findElement(orderDurationPlaceholder).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderDurationPlaceholder));

        driver.findElement(By.xpath(".//div[text()='" + orderDuration + "']")).click();

        if (color.equals("чёрный жемчуг")){
            driver.findElement(blackColorCheckbox).click();
        } else if (color.equals("серая безысходность")) {
            driver.findElement(greyColorCheckbox).click();
        }

        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(orderButton).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(confirmationWindow));
        driver.findElement(yesButton).click();
    }

    /**
     * Проверка об успешности создания заказа
     * @return появление модального окна с номером заказа
     */
    public boolean successfullyText() {
        var successFullOrder = driver.findElement(modalWindow).getText();
        return successFullOrder.contains("Заказ оформлен");
    }

}
