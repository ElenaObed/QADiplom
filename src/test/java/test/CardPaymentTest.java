package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
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
        open("http://localhost:8080");
        Sql.clearDB();
    }


    // POSITIVE
    @Test
    public void shouldDoPaymentByDebitCardWithStatusApproved() {
        val paymentPage = startPage.getDebitCardPayment();
        val info = DataHelper.getValidApprovedCardNumber();
        paymentPage.fillPaymentFormat(info);
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", Sql.getPaymentStatus());
    }
}
