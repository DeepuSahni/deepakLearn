package main.advice;

import main.dictionary.DictionaryLoader;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


@Test
public class AdviceServiceTest{
    private AdviceService adviceService = new AdviceService(DictionaryLoader.getDictionary());


    public void given_aWord_when_getSuggestions_then_getAllPossibleWords() {
        List<String> suggestionsForWord = adviceService.getSuggestionsForWord("CATCHMENEVER");
        List<String> expected = Arrays.asList("CATCH-ME-NEVER", "CATCH-MEN-EVER", "CA-TCH-ME-NEVER", "CA-TCH-MEN-EVER");
        Assert.assertEquals(suggestionsForWord.size(), 4);
        MatcherAssert.assertThat(suggestionsForWord, containsInAnyOrder(expected.toArray()));
    }

    public void given_aWordMixedWithNumbers_when_getSuggestions_then_getAllPossibleWords() {
        List<String> suggestionsForWord = adviceService.getSuggestionsForWord("CATCHME1NEVER");
        Assert.assertEquals(suggestionsForWord.size(), 2);
        List<String> expected = Arrays.asList("CATCH-ME-1-NEVER", "CA-TCH-ME-1-NEVER");
        MatcherAssert.assertThat(suggestionsForWord, containsInAnyOrder(expected.toArray()));
    }

    public void given_atomicWord_when_getSuggestions_then_getOnlyOneWord() {
        List<String> suggestionsForWord = adviceService.getSuggestionsForWord("AM");
        Assert.assertEquals(suggestionsForWord.size(), 1);
        Assert.assertTrue(suggestionsForWord.contains("AM"));
    }

    public void given_wordWithOnlyNumber_when_getSuggestions_then_getOnlyNumber() {
        List<String> suggestionsForWord = adviceService.getSuggestionsForWord("1");
        Assert.assertEquals(suggestionsForWord.size(), 1);
        Assert.assertTrue(suggestionsForWord.contains("-1-"));
    }

    public void given_atomicWordNotInDictionary_when_getSuggestions_then_getNothing() {
        List<String> suggestionsForWord = adviceService.getSuggestionsForWord("AL");
        Assert.assertEquals(suggestionsForWord.size(), 0);
    }
}
