package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final SelenideElement heading = $$(".heading").findBy(exactText("Оплата по карте"));
    SelenideElement form = $(".form");

    private SelenideElement cardNumberField = form.$("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthsField = form.$("input[placeholder='08']");
    private final SelenideElement yearsField = form.$("input[placeholder='22']");
    private final SelenideElement ownerField = $$(".input").find(exactText("Владелец")).$(".input__control");
    private final SelenideElement cvcField = form.$("input[placeholder='999']");
    private final SelenideElement buttonContinue = form.$$("button").findBy(exactText("Продолжить"));
    private final SelenideElement notificationOk = $(".notification_status_ok");
    private final SelenideElement notificationError = $(".notification_status_error");
    private final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidCardExpirationDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));

    public void fillPaymentFormat(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthsField.setValue(info.getMonth());
        yearsField.setValue(info.getYear());
        ownerField.setValue(info.getOwner());
        cvcField.setValue(info.getCvc());
        buttonContinue.click();
    }

    public void checkSuccessNotification() {

        notificationOk.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void checkErrorNotification() {

        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkWrongFormat() {
        wrongFormat.shouldBe(Condition.visible);
    }

    public void checkInvalidCardExpirationDate() {

        invalidCardExpirationDate.shouldBe(Condition.visible);
    }

    public void verifyCardExpired() {

        cardExpired.shouldBe(Condition.visible);
    }

    public void verifyEmptyField() {

        emptyField.shouldBe(Condition.visible);
    }
}
