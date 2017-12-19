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
        Stream<String> encodedStream = service.encode("(225)56 .3");
        Assert.assertTrue(encodedStream.anyMatch(encoded -> encoded.equalsIgnoreCase("CALLME")));
    }

    public void given_allDigitsNotInEncodingDictionary_when_encode_then_ignoreNumber() {
        Stream<String> encodedStream = service.encode("111.111");
        Assert.assertEquals(encodedStream.count(), 0);
    }
    public void given_oneDigitNotInEncodingDictionary_when_encode_then_ChangeTheRest() {
        Stream<String> encodedStream = service.encode("212");
        assertThat(encodedStream, StreamMatchers.contains("A1A","B1A","C1A","A1B","B1B","C1B","A1C","B1C","C1C"));
    }

    public void given_someDigitsNotInEncodingDictionary_when_encode_then_ChangeTheRest() {
        Stream<String> encodedStream = service.encode("2121");
        assertThat(encodedStream, StreamMatchers.contains("A1A1","B1A1","C1A1","A1B1","B1B1","C1B1","A1C1","B1C1","C1C1"));
    }

    public void given_consecutiveDigitsNotInEncodingDictionary_when_encode_then_IgnoreNumber() {
        Stream<String> encodedStream = service.encode("1010");
        Assert.assertEquals(encodedStream.count(), 0);
    }
}
