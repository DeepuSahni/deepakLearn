package main.encode;

import co.unruly.matchers.StreamMatchers;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

@Test
public class EncodeServiceTest {
    private EncodeService service = new EncodeService();

    public void given_singleDigit_when_encode_then_returnSingleLetter() {
        Stream<String> encoded = service.encode("2");
        assertThat(encoded, StreamMatchers.contains("A", "B", "C"));
    }

    public void given_multipleDigits_when_encode_then_returnMultipleLetters() {
        Stream<String> encodedStream = service.encode("225563");
        Assert.assertTrue(encodedStream.anyMatch(encoded -> encoded.equalsIgnoreCase("CALLME")));
    }

    public void given_multipleDigitsWithPunctuation_when_encode_then_ignorePunctuation() {
        Stream<String> encodedStream = service.encode("(225)56.3");
        Assert.assertTrue(encodedStream.anyMatch(encoded -> encoded.equalsIgnoreCase("CALLME")));
    }

}
