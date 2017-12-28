package main.result;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PhoneNumberMapperTest {
    public void given_validNumber_when_map_then_getValidProcessorObject() {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberMapper().apply("123456789");
        Assert.assertEquals(phoneNumberProcessor.getPhoneNumber(), "123456789");
    }

    public void given_validNumberWithPunctuation_when_map_then_getValidProcessorObject() {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberMapper().apply("(123)4.567.8-9");
        Assert.assertEquals(phoneNumberProcessor.getPhoneNumber(), "(123)4.567.8-9");
    }

    public void given_aJunkNumber_when_map_then_getValidProcessorObject() {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberMapper().apply("thisIsNotANumber");
        Assert.assertEquals(phoneNumberProcessor.getPhoneNumber(), "thisIsNotANumber");
    }

    public void given_emptyNumber_when_map_then_getValidProcessorObject() {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberMapper().apply("");
        Assert.assertEquals(phoneNumberProcessor.getPhoneNumber(), "");
    }

    public void given_nullNumber_when_map_then_getValidProcessorObject() {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberMapper().apply(null);
        Assert.assertNull(phoneNumberProcessor.getPhoneNumber());
    }
}