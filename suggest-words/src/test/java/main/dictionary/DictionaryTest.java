package main.dictionary;


import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

@Test
public class DictionaryTest {
    public static final String USER_DICTIONARY = "userDictionary.txt";
    public void given_noDictionaryOption_when_getDictionary_then_loadDefaultDictionary() {
        Stream<String> dictionary = Dictionary.getDictionary();
        // Yes, there are 2,35,886 words in the default dictionary
        assertEquals(dictionary.count(), 235886);
    }

    public void given_userDictionaryOption_when_getDictionary_then_loadUserDictionary() {
        System.setProperty(Dictionary.DICTIONARY_OPTION, USER_DICTIONARY);
        Stream<String> dictionary = Dictionary.getDictionary();
        assertEquals(dictionary.count(), 50000);
    }
}