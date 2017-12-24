package main.dictionary;


import main.util.Util;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class DictionaryLoaderTest {
    private static final String USER_DICTIONARY = "userDictionary.txt";
    private static final String DICTIONARY = "dictionary";

    public void given_noDictionaryOption_when_getDictionary_then_loadDefaultDictionary() {
        System.clearProperty(DICTIONARY);
        assertEquals(DictionaryLoader.getDictionary().size(), 234367);
    }

    public void given_defaultDictionaryIsSet_when_getDictionary_then_AllWordsAreInUpperCase() {
        Set<String> userDictionary = DictionaryLoader.getDictionary();
        // Get count of all words that are NOT in Upper case
        assertEquals(userDictionary.stream().filter(word -> word.matches(Util.NOT_AN_UPPER_CASE_REGEX)).count(), 0);
        // Get count of all words that are IN Upper case
        assertEquals(userDictionary.stream().filter(word -> word.matches("[A-Z]+")).count(), 234367);
    }

    public void given_userDictionaryOption_when_getDictionary_then_loadUserDictionary() {
        System.setProperty(DICTIONARY, USER_DICTIONARY);
        assertEquals(DictionaryLoader.getDictionary().size(), 49637);
        System.clearProperty(DICTIONARY);
    }

    public void given_userDictionaryHasPunctuations_when_getDictionary_then_PunctuationIsIgnored() {
        System.setProperty(DICTIONARY, USER_DICTIONARY);
        Set<String> userDictionary = DictionaryLoader.getDictionary();
        // Blank lines are gone
        assertEquals(userDictionary.size(), 49637);
        // ,,, are gone from AARON
        assertTrue(userDictionary.contains("AARON"));
        assertFalse(userDictionary.contains("Aaron,,,"));
        System.clearProperty(DICTIONARY);
    }

    public void given_userDictionaryIsSet_when_getDictionary_then_AllWordsAreInUpperCase() {
        System.setProperty(DICTIONARY, USER_DICTIONARY);
        Set<String> userDictionary = DictionaryLoader.getDictionary();
        // Get count of all words that are NOT in Upper case
        assertEquals(userDictionary.stream().filter(word -> word.matches(Util.NOT_AN_UPPER_CASE_REGEX)).count(), 0);
        // Get count of all words that are IN Upper case
        assertEquals(userDictionary.stream().filter(word -> word.matches("[A-Z]+")).count(), 49637);
        System.clearProperty(DICTIONARY);
    }
}
