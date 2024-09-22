import PageObjects.MainPage;
import PageObjects.OrderPage;
import PageObjects.RentInfoPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

/**
 * Класс тестов по проверке оформления заказа самоката
 */
@RunWith(Parameterized.class)
public class CreateOrderTest {

    /**
     * Веб-драйвер
     */
    private WebDriver driver;

    //region Параметры теста
    private final String userName;
    private final String fullName;
    private final String address;
    private final String phoneNumber;
    private final String undergroundName;
    private final String orderDate;
    private final String orderDuration;
    private final String color;
    private final String comment;
    //endregion

    /**
     * Конструктор класса
     * @param userName Имя
     * @param fullName Фамилия
     * @param address Адрес доставки
     * @param phoneNumber Номер телефона
     * @param undergroundName Название метро
     * @param orderDate Дата заказа
     * @param orderDuration Длительность заказа
     * @param color Цвет самоката
     * @param comment Комментарий
     */
    public CreateOrderTest(String userName, String fullName, String address, String phoneNumber,
                           String undergroundName, String orderDate, String orderDuration, String color, String comment){
        this.userName = userName;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.undergroundName = undergroundName;
        this.orderDate = orderDate;
        this.orderDuration = orderDuration;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return new Object[][]{
                {"Эльвира", "Попова", "Спортивна,120" ,"89178762617", "Сокольники", "Choose суббота, 21-е сентября 2024 г.", "двое суток", "чёрный жемчуг","Позвонить за 30 минут"},
                {"Иван", "Иванов", "Спортивна" ,"89178733317", "Спортивная", "Choose пятница, 20-е сентября 2024 г.", "трое суток", "серая безысходность", "Не звонить в домофон"}
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    /**
     * Заказ по верхней кнопке
     */
    @Test
    public void TestUpperCreateOrderButton() {
        // Главная страница
        var mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButton();

        // Страница заказа самоката
        var orderPage = new OrderPage(driver);
        orderPage.feelOrderForm(userName, fullName, address, phoneNumber, undergroundName);
        orderPage.clickNextButton();

        // Страница Про Аренду
        var rentInfoPage = new RentInfoPage(driver);
        rentInfoPage.feelOrderForm(orderDate, orderDuration, color, comment);
        assertTrue(rentInfoPage.successfullyText());
    }

    /**
     * Заказ по верхней кнопке
     */
    @Test
    public void TestBottomCreateOrderButton() {
        // Главная страница
        var mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButtonLower();

        // Страница заказа самоката
        var orderPage = new OrderPage(driver);
        orderPage.feelOrderForm(userName, fullName, address, phoneNumber, undergroundName);
        orderPage.clickNextButton();

        // Страница Про Аренду
        var rentInfoPage = new RentInfoPage(driver);
        rentInfoPage.feelOrderForm(orderDate, orderDuration, color, comment);
        assertTrue(rentInfoPage.successfullyText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


