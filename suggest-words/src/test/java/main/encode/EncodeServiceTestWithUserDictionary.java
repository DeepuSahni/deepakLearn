package main.encode;

import co.unruly.matchers.StreamMatchers;

import main.dictionary.DictionaryLoader;
import main.dictionary.DictionaryLoaderTest;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

@Test
public class EncodeServiceTestWithUserDictionary {
    private EncodeService service = new EncodeService();
    private static final String dictionary = "dictionary";

    @BeforeClass
    public void setUp() {
        System.out.println("set");
        System.setProperty(dictionary, DictionaryLoaderTest.USER_DICTIONARY);
    }

    @AfterClass
    public void cleanUp(){
        System.out.println("clean");
        System.clearProperty(dictionary);
    }

    public void given_allDigitsNotInDictionary_when_encode_then_ignoreNumber() {
        Stream<String> encodedStream = service.encode("999.999");
        Assert.assertEquals(encodedStream.count(), 0);
    }
    public void given_oneDigitNotInDictionary_when_encode_then_ChangeTheRest() {
        Stream<String> encodedStream = service.encode("292");
        assertThat(encodedStream, StreamMatchers.contains("A9A","B9A","C9A","A9B","B9B","C9B","A9C","B9C","C9C"));
    }

    public void given_someDigitsNotInDictionary_when_encode_then_ChangeTheRest() {
        Stream<String> encodedStream = service.encode("2929");
        assertThat(encodedStream, StreamMatchers.contains("A9A9","B9A9","C9A9","A9B9","B9B9","C9B9","A9C9","B9C9","C9C9"));
    }

    public void given_consecutiveDigitsNotInDictionary_when_encode_then_IgnoreNumber() {
        Stream<String> encodedStream = service.encode("2992");
        Assert.assertEquals(encodedStream.count(), 0);
    }
}
