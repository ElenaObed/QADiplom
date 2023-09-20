package data;
import lombok.Value;

public class DataHelper {

    public static DataGenerator dataGenerator = new DataGenerator();

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    //номер карты

    public static CardInfo getValidApprovedCardNumber() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getValidDeclinedCardNumber() {
        return new CardInfo(DataGenerator.getDeclinedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getCardNumberEmpty() {
        return new CardInfo(DataGenerator.getEmptyCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }
    public static CardInfo getInvalidCardNumberWithFifteenDigits() {
        return new CardInfo(DataGenerator.getIncorrectCardNumberWithFifteenNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }
    public static CardInfo getAnotherBankCardNumber() {
        return new CardInfo(DataGenerator.getAnotherCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    //месяц

    public static CardInfo getFieldMonthEmpty() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getEmptyMonthField(), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidMonthTwoNumber() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getInvalidMonth(), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    //год

    public static CardInfo getInvalidYearWithOneNumber() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getIncorrectYear(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }
    public static CardInfo getFieldYearEmpty() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getEmptyYearField(), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }
    public static CardInfo getCardWithExpiredYear() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(-1), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }
    public static CardInfo getInvalidYearExceedingCardExpirationDate() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(4), DataGenerator.getValidOwner(), DataGenerator.getValidCVC());
    }

    // пользователь
    public static CardInfo getInvalidOwnerWithCyrillic() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFieldOnCyrillic(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerWithOneName() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerFirstName(), DataGenerator.getValidCVC());
    }
    public static CardInfo getInvalidOwnerOneLetter() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerOneLetter(), DataGenerator.getValidCVC());
    }
    public static CardInfo getInvalidOwnerWithNumber() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerNumber(), DataGenerator.getValidCVC());
    }

    public static CardInfo getInvalidOwnerSymbols() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerSymbols(), DataGenerator.getValidCVC());
    }
    public static CardInfo getInvalidOwnerNumberOfLetters() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getInvalidOwnerManyLetters(), DataGenerator.getValidCVC());
    }

    public static CardInfo getOwnerFieldEmpty() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getEmptyOwner(), DataGenerator.getValidCVC());
    }

    //CVC/CVV
    public static CardInfo getInvalidCVCWithOneDigit() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getIncorrectCVCWithOneNumber());
    }

    public static CardInfo getEmptyCVCField() {
        return new CardInfo(DataGenerator.getApprovedCardNumber(), DataGenerator.getShiftedMonthFromNow(1), DataGenerator.getShiftedYearFromNow(1), DataGenerator.getValidOwner(), DataGenerator.getEmptyCVC());
    }

}