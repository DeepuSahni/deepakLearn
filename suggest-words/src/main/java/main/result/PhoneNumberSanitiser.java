package main.result;

import main.util.Util;

import java.util.Optional;

public class PhoneNumberSanitiser {

    /**
     * Private constructor for this Utility class
     */
    private PhoneNumberSanitiser() {
    }

    public static Optional<String> getSanitisedPhoneNumber(final String phoneNumber) {
        Optional<String> sanitised = Optional.ofNullable(phoneNumber);
        if (!sanitised.isPresent()) {
            return sanitised;
        }
        String onlyNumbers = phoneNumber.replaceAll(Util.NOT_NUMBER_REGEX, "");
        if (sanitised.isPresent() && onlyNumbers.length() <=10 && phoneNumber.matches(Util.MIN_ONE_DIGIT_REGEX) && !phoneNumber.matches(Util.HAS_A_LETTER_REGEX)) {
            sanitised = Optional.of(onlyNumbers);
        }
        else {
            sanitised = Optional.ofNullable(null);
        }
        return sanitised;
    }
}
