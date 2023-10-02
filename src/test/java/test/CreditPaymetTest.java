package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.StartPage;
import sql.Sql;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPaymetTest {

    StartPage startPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        Sql.clearDB();
    }

    @BeforeEach
    public void setup() {
        startPage = open(System.getProperty("sut.url"), StartPage.class);
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


        //Negative Credit card

        //Номер карты
        @Test
        public void shouldNotDoPaymentByCreditCardAnotherBankCard () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getAnotherBankCardNumber();
            creditPage.fillPaymentFormat(info);
            creditPage.checkErrorNotification();
        }

        @Test
        public void shouldNotDoPaymentByCreditCardFifteenDigits () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidCardNumberWithFifteenDigits();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }

        @Test
        public void shouldNotDoPaymentByCreditCardNumberEmpty () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getCardNumberEmpty();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }

        //Месяц

        @Test
        public void shouldNotDoPaymentByCreditCardMonthEmpty () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getFieldMonthEmpty();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }

        @Test
        public void shouldNotDoPaymentByCreditCardWhenFieldMonthInvalNumber () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidMonthInvalNumber();
            creditPage.fillPaymentFormat(info);
            creditPage.checkInvalidCardExpirationDate();
        }

        //Год
        @Test
        public void shouldNotDoPaymentByCreditCardYearEmpty () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getFieldYearEmpty();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardWhenYearWithWithOneNumber () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidYearWithOneNumber();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardWithExpiredYear () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getCardWithExpiredYear();
            creditPage.fillPaymentFormat(info);
            creditPage.verifyCardExpired();
        }

        // юзер
        @Test
        public void shouldNotDoPaymentByCreditCardWhenOwnerCyrillic () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidOwnerWithCyrillic();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardInvalidOwnerWithOneName () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidOwnerWithOneName();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }

        @Test
        public void shouldNotDoPaymentByCreditCardIfOwnerOneLetter () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidOwnerOneLetter();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardIfOwnerWithNumber () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidOwnerWithNumber();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardIfOwnerSymbols () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidOwnerSymbols();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardIfOwnerNumberOfLetters () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidOwnerNumberOfLetters();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardIfOwnerFieldEmpty () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getOwnerFieldEmpty();
            creditPage.fillPaymentFormat(info);
            creditPage.verifyEmptyField();
        }

        //CVC/CVV
        @Test
        public void shouldNotDoPaymentByCreditCardIfCVCWithOneDigit () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getInvalidCVCWithOneDigit();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }
        @Test
        public void shouldNotDoPaymentByCreditCardIfEmptyCVCField () {
            var creditPage = startPage.getBuyInCredit();
            var info = DataHelper.getEmptyCVCField();
            creditPage.fillPaymentFormat(info);
            creditPage.checkWrongFormat();
        }


}



