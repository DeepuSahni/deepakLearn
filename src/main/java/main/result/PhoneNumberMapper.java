package main.result;

import java.util.function.Function;

/**
 * Mapper class for converting a phone number into PhoneNumberProcessor object.
 */
public class PhoneNumberMapper implements Function<String, PhoneNumberProcessor>{

    @Override
    public PhoneNumberProcessor apply(final String phoneNumber) {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberProcessor();
        phoneNumberProcessor.setPhoneNumber(phoneNumber);
        return phoneNumberProcessor;
    }

}
