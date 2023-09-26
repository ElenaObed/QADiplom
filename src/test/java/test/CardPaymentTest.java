package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.PaymentPage;
import page.StartPage;
import sql.Sql;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardPaymentTest {
    StartPage startPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setup() {
      startPage = open("http://localhost:8080", StartPage.class);
    }


    // POSITIVE
    @Test
    public void shouldDoPaymentByDebitCardWithStatusApproved() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getValidApprovedCardNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkSuccessNotification();
        var status = Sql.getPaymentStatus();
        assertEquals("APPROVED", status);
    }
    @Test
    public void shouldDoPaymentByDebitCardWithStatusDeclined() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getValidDeclinedCardNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
        var status = Sql.getPaymentStatus();
        assertEquals("DECLINED", status);
    }

    @Test
    public void shouldDoPaymentByCreditCardWithStatusApproved() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getValidApprovedCardNumber();
        creditPage.fillPaymentFormat(info);
        creditPage.checkSuccessNotification();
        var status = Sql.getCreditStatus();
        assertEquals("APPROVED", status);
    }

    @Test
    public void shouldDoPaymentByCreditCardWithStatusDeclined() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getValidDeclinedCardNumber();
        creditPage.fillPaymentFormat(info);
        creditPage.checkSuccessNotification();
        var status = Sql.getCreditStatus();
        assertEquals("DECLINED", status);
    }

    //Negative Debit card
       //Номер карты
    @Test
    public void shouldNotDoPaymentByDebitCardAnotherBankCard() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getAnotherBankCardNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkErrorNotification();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardFifteenDigits() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidCardNumberWithFifteenDigits();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardNumberEmpty() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getCardNumberEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    //Месяц

    @Test
    public void shouldNotDoPaymentByDebitCardMonthEmpty() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getFieldMonthEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenFieldMonthInvalNumber() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidMonthInvalNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkInvalidCardExpirationDate();
    }

    //Год
    @Test
    public void shouldNotDoPaymentByDebitCardYearEmpty() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getFieldYearEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardWhenYearWithWithOneNumber() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidYearWithOneNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardWithExpiredYear() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getCardWithExpiredYear();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyCardExpired();
    }

    // юзер
    @Test
    public void shouldNotDoPaymentByDebitCardWhenOwnerCyrillic() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidOwnerWithCyrillic();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardInvalidOwnerWithOneName() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidOwnerWithOneName();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerOneLetter() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidOwnerOneLetter();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerWithNumber() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidOwnerWithNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerSymbols() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidOwnerSymbols();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerNumberOfLetters() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidOwnerNumberOfLetters();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardIfOwnerFieldEmpty() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getOwnerFieldEmpty();
        paymentPage.fillPaymentFormat(info);
        paymentPage.verifyEmptyField();
    }

    //CVC/CVV
    @Test
    public void shouldNotDoPaymentByDebitCardIfCVCWithOneDigit() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getInvalidCVCWithOneDigit();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByDebitCardIfEmptyCVCField() {
        var paymentPage = startPage.getDebitCardPayment();
        var info = DataHelper.getEmptyCVCField();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkWrongFormat();
    }


    //Negative Credit card

    //Номер карты
    @Test
    public void shouldNotDoPaymentByCreditCardAnotherBankCard() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getAnotherBankCardNumber();
        creditPage.fillPaymentFormat(info);
        creditPage.checkErrorNotification();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardFifteenDigits() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidCardNumberWithFifteenDigits();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardNumberEmpty() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getCardNumberEmpty();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }

    //Месяц

    @Test
    public void shouldNotDoPaymentByCreditCardMonthEmpty() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getFieldMonthEmpty();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardWhenFieldMonthInvalNumber() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidMonthInvalNumber();
        creditPage.fillPaymentFormat(info);
        creditPage.checkInvalidCardExpirationDate();
    }

    //Год
    @Test
    public void shouldNotDoPaymentByCreditCardYearEmpty() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getFieldYearEmpty();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardWhenYearWithWithOneNumber() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidYearWithOneNumber();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardWithExpiredYear() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getCardWithExpiredYear();
        creditPage.fillPaymentFormat(info);
        creditPage.verifyCardExpired();
    }

    // юзер
    @Test
    public void shouldNotDoPaymentByCreditCardWhenOwnerCyrillic() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidOwnerWithCyrillic();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardInvalidOwnerWithOneName() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidOwnerWithOneName();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }

    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerOneLetter() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidOwnerOneLetter();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerWithNumber() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidOwnerWithNumber();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerSymbols() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidOwnerSymbols();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerNumberOfLetters() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidOwnerNumberOfLetters();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardIfOwnerFieldEmpty() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getOwnerFieldEmpty();
        creditPage.fillPaymentFormat(info);
        creditPage.verifyEmptyField();
    }

    //CVC/CVV
    @Test
    public void shouldNotDoPaymentByCreditCardIfCVCWithOneDigit() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getInvalidCVCWithOneDigit();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
    @Test
    public void shouldNotDoPaymentByCreditCardIfEmptyCVCField() {
        var creditPage = startPage.getBuyInCredit();
        var info = DataHelper.getEmptyCVCField();
        creditPage.fillPaymentFormat(info);
        creditPage.checkWrongFormat();
    }
}

