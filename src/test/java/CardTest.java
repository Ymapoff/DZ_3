import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class CardTest {


    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
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
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void invalidDataTestNoName() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("");
        form.$("[data-test-id=phone] input").sendKeys("+79998887766");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void invalidDataTestNoPhone() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));


    }

    @Test
    void invalidDataTestPhone() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("+7999888776");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }


    @Test
    void invalidDataTestNoCheckbox() {

        SelenideElement form = $("[class]");
        form.$("[data-test-id=name] input").sendKeys("Морозов Павлик");
        form.$("[data-test-id=phone] input").sendKeys("+79998887766");
        form.$("[data-test-id=agreement]");
        form.$("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));


    }


}