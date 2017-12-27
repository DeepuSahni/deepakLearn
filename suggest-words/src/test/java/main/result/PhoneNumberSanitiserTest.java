package main.result;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Stream;

@Test
public class PhoneNumberSanitiserTest {
    private static final String VALID_NUMBER = "1234567890";


    public void given_numberWithSpaces_when_sanitise_then_ignorePunctuation() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("123 456 789 0").get(), VALID_NUMBER);
    }

    public void given_numberWithParenthesis_when_sanitise_then_ignorePunctuation() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("(123)(456)7890").get(), VALID_NUMBER);
    }

    public void given_numberWithCommas_when_sanitise_then_ignorePunctuation() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("(123,456,7890").get(), VALID_NUMBER);
    }

    public void given_numberWithDots_when_sanitise_then_ignorePunctuation() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("(123.456.789.0").get(), VALID_NUMBER);
    }

    public void given_numberWithDashes_when_sanitise_then_ignorePunctuation() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("(123-456-7890").get(), VALID_NUMBER);
    }

    public void given_numberWithMixedPunctuation_when_sanitise_then_ignorePunctuation() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("(123,456,789-0").get(), VALID_NUMBER);
    }

    public void given_emptyNumber_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber("").isPresent());
    }

    public void given_nullNumber_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber(null).isPresent());
    }

    public void given_numberWithLettersAtBeginning_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber("(Aa-1234567890").isPresent());
    }

    public void given_numberWithLettersAtEnd_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber("(1234567890abc").isPresent());
    }

    public void given_numberWithLettersEverywhere_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber("(a1a2345c678x90x").isPresent());
    }

    public void given_numberWithNoDigits_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber("(,.-()").isPresent());
    }

    public void given_numberWithOnlyLetters_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber("CALLME").isPresent());
    }

    public void given_numberLongerThanTenDigits_when_sanitise_then_getNothing() {
        Assert.assertFalse(PhoneNumberSanitiser.getSanitisedPhoneNumber("(1234567890-123").isPresent());
    }

    public void given_numberEqualToTenDigits_when_sanitise_then_getSanitisedNumber() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("(1234567890").get(), VALID_NUMBER);
    }

    public void given_numberLessThanTenDigits_when_sanitise_then_getSanitisedNumber() {
        Assert.assertEquals(PhoneNumberSanitiser.getSanitisedPhoneNumber("(123.456").get(), "123456");
    }
}
