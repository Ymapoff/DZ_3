import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class cardTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void shouldTestApplication() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("+79998887766");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();


        Thread.sleep(5000);
    }

}