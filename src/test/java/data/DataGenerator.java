package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    //Номер карты

    public static String getApprovedCardNumber() {

        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {

        return "4444 4444 4444 4442";
    }

    public static String getEmptyCardNumber() {

        return "";
    }

    public static String getIncorrectCardNumberWithFifteenNumber() {

        return "4444 4444 4444 444";
    }

    public static String getAnotherCardNumber() {

        return "4444 4444 4444 4443";
    }

    //Месяц

    public static String getShiftedMonthFromNow(int addMonth) {
        return LocalDate.now().plusMonths(addMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInvalidMonth() {

        return "13";
    }

    public static String getEmptyMonthField() {

        return "";
    }

    //Год

    public static String getShiftedYearFromNow(int addYears) {
        return LocalDate.now().plusYears(addYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getIncorrectYear() {

        return "2";
    }

    public static String getEmptyYearField() {

        return "";
    }

    //user

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidOwnerFieldOnCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidOwnerFirstName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName();
    }

    public static String getInvalidOwnerOneLetter() {

        return "A";
    }

    public static String getInvalidOwnerManyLetters() {

        return "Aaaaaaaaaaaaaaaaaaaaaaaaaa";
    }

    public static String getInvalidOwnerNumber() {

        return "12345";
    }

    public static String getInvalidOwnerSymbols() {

        return "@$%&*&!?";
    }

    public static String getEmptyOwner() {

        return "";
    }

    //  CVC/CVV

    public static String getValidCVC() {
        Faker faker = new Faker();
        return faker.numerify("###");
    }

    public static String getIncorrectCVCWithOneNumber() {

        return "1";
    }

    public static String getEmptyCVC() {

        return "";
    }

}
