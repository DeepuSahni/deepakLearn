package main.dictionary;


import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

@Test
public class DictionaryLoaderTest {
    public static final String USER_DICTIONARY = "userDictionary.txt";
    public void given_noDictionaryOption_when_getDictionary_then_loadDefaultDictionary() {
        assertEquals(DictionaryLoader.getDictionary().size(), 234367);
    }

    public void given_userDictionaryOption_when_getDictionary_then_loadUserDictionary() {
        System.setProperty("dictionary", USER_DICTIONARY);
        assertEquals(DictionaryLoader.getDictionary().size(), 49637);
        System.clearProperty("dictionary");
    }

}
