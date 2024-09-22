package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * POM главной станицы сайта
 */
public class MainPage {

    public static final String COMMON_ANSWER = "Answer not found!";

    /**
     * Веб-драйвер
     */
    private final WebDriver driver;

    /**
     * Локатор для баннера Куки
     */
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    /**
     * FAQ лист
     */
    private final By faqList = By.className("accordion__button");

    //region Локаторы для вопросов
    public final String question_0 = "accordion__heading-0";
    public final String question_1 = "accordion__heading-1";
    public final String question_2 = "accordion__heading-2";
    public final String question_3 = "accordion__heading-3";
    public final String question_4 = "accordion__heading-4";
    public final String question_5 = "accordion__heading-5";
    public final String question_6 = "accordion__heading-6";
    public final String question_7 = "accordion__heading-7";
    // endregion

    //region Локаторы для ответов
    private final By answer_0 = By.id("accordion__panel-0");
    private final By answer_1 = By.id("accordion__panel-1");
    private final By answer_2 = By.id("accordion__panel-2");
    private final By answer_3 = By.id("accordion__panel-3");
    private final By answer_4 = By.id("accordion__panel-4");
    private final By answer_5 = By.id("accordion__panel-5");
    private final By answer_6 = By.id("accordion__panel-6");
    private final By answer_7 = By.id("accordion__panel-7");
    //endregion

    /**
     * Локаторы для верхней кнопки заказать
     */
    private final By orderButton = By.className("Button_Button__ra12g");

    /**
     * Локатор для нижней кнопки заказать
     */
    private final By orderButtonLower = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    /**
     * Словарь вопрос-ответ для проверки отображаемых ответов
     */
    private final Map<String, By> questionToAnswer = new HashMap<>();

    /**
     * Конструктор
     * @param driver Веб-драйвер
     */
    public MainPage(WebDriver driver){
        this.driver = driver;
        fillDictionary();
    }

    private void fillDictionary() {
        questionToAnswer.put(question_0, answer_0);
        questionToAnswer.put(question_1, answer_1);
        questionToAnswer.put(question_2, answer_2);
        questionToAnswer.put(question_3, answer_3);
        questionToAnswer.put(question_4, answer_4);
        questionToAnswer.put(question_5, answer_5);
        questionToAnswer.put(question_6, answer_6);
        questionToAnswer.put(question_7, answer_7);
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
     * Проверить весь список FAQ
     * @param question текст вопроса
     * @return Ответ на полученный в тесте вопрос
     * Если ответ из словаря не находиться - возвращаем общий ответ
     */
    public String getAnswerFaqListElement(String question){
        List<WebElement> elements = getListElements();
        for (WebElement element : elements) {
            if(element.getText().equals(question)) {
                scrollToFaqListToElement(element);
                return getAnswer(element);
            }
        }
        return COMMON_ANSWER;
    }

    /**
     * Получить ответ на вопрос
     * @param question веб-элемент вопроса
     * @return текст ответа
     */
    public String getAnswer(WebElement question) {
        question.click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(driver.findElement(questionToAnswer.get(question.getAttribute("id")))));

        return driver.findElement(questionToAnswer.get(question.getAttribute("id"))).getText();
    }

    /**
     * Нажать на кнопку Заказать
     */
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));
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
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));
    }
}
