import PageObjects.MainPage;
import PageObjects.OrderPage;
import PageObjects.RentInfoPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class ChromeTest {
    private WebDriver driver;

    private String userName;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String undergroundName;
    private String orderDate;
    private String orderDuration;

    public ChromeTest(String userName, String fullName, String address, String phoneNumber,
                      String undergroundName, String orderDate, String orderDuration){
        this.userName = userName;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.undergroundName = undergroundName;
        this.orderDate = orderDate;
        this.orderDuration = orderDuration;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][]{
                {"Эльвира", "Попова", "Спортивна,120" ,"89178762617", "Сокольники", "Choose суббота, 21-е сентября 2024 г.", "двое суток"},
                {"Иван", "Иванов", "Спортивна" ,"89178733317", "Спортивная", "Choose пятница, 20-е сентября 2024 г.", "трое суток"}
        };
    }

    @Test
    public void TestDropDownList(){
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        var mainPage = new MainPage(driver);
        mainPage.clickOnFaqListElements();
    }


    @Test
    public void TestCreateOrder() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Главная страница
        var mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButton();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));

        // Страница заказа самоката
        var orderPage = new OrderPage(driver);
        orderPage.feelOrderForm(userName, fullName, address, phoneNumber, undergroundName);
        orderPage.clickNextButton();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));

        // Страница Про Аренду
        var rentInfoPage = new RentInfoPage(driver);
        rentInfoPage.feelOrderForm(orderDate, orderDuration);

    }

    @Test
    public void TestBottomCreateOrder() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        var mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButtonLower();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));

        // Страница заказа самоката
        var orderPage = new OrderPage(driver);
        orderPage.feelOrderForm(userName, fullName, address, phoneNumber, undergroundName);
        orderPage.clickNextButton();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));

        // Страница Про Аренду
        var rentInfoPage = new RentInfoPage(driver);
        rentInfoPage.feelOrderForm(orderDate, orderDuration);
    }



    @After
    public void tearDown() {
        driver.quit();
    }
}


