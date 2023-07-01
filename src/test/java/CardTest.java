import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CardTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        open("http://localhost:9999");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void shouldTestApplication() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("+79998887766");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void invalidDataTestName() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Pupkina Aliona");
        form.$("[data-test-id=phone] input").sendKeys("+79998887766");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = $(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);

    }

    @Test
    void invalidDataTestNoName() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("");
        form.$("[data-test-id=phone] input").sendKeys("+79998887766");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String expected = "Поле обязательно для заполнения";
        String actual = $(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);

    }

    @Test
    void invalidDataTestNoPhone() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String expected = "Поле обязательно для заполнения";
        String actual = $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);

    }

    @Test
    void invalidDataTestPhone() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("+7999888776");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);

    }


    @Test
    void invalidDataTestNoCheckbox() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("+79998887766");
        form.$("[data-test-id=agreement]");
        form.$("button").click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = $(By.cssSelector("[data-test-id=agreement].input_invalid .checkbox__text")).getText().trim();
        assertEquals(expected, actual);

    }


}