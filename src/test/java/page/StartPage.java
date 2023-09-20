package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.w3c.dom.html.HTMLInputElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private final SelenideElement heading = $(".heading").shouldBe(Condition.text("Путешествие дня"));
    private final SelenideElement buttonBuy = $$("button").findBy(Condition.text("Купить"));
    private final SelenideElement creditButton = $$("button").findBy(Condition.text("Купить в кредит"));


    public void StartChoosePage() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentPage getDebitCardPayment() {
        buttonBuy.click();
        return new PaymentPage();
    }

    public CreditPage getBuyInCredit() {
        creditButton.click();
        return new CreditPage();
    }
}
