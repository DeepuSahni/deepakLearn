package main.advice;

import main.util.Util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Should take in encoded words and return matches if any.
 */
public class AdviceService {
    private final Set<String> dictionary;
    private static final String AT_LEAST_ONE_DIGIT_REGEX = ".*\\d+.*";

    public AdviceService(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getSuggestionsForWord(final String word) {
        List<String> suggestions = new ArrayList<>();
        List<List<String>> wordBrokenIntoDictionaryWords = new ArrayList<>();
        String[] splitWord = word.split("((?<=[0-9])|(?=[0-9]))");

        for (int i = 0; i < splitWord.length; i++) {
            if (splitWord[i].matches("[0-9]")){
                // Add the digit as it is.
                wordBrokenIntoDictionaryWords.add(new ArrayList<>(Arrays.asList("-" + splitWord[i] + "-")));
            }
            else {
                // Get dictionary words within a word.
                List<String> dictionaryWords = this.breakWordIntoDictionaryWords(splitWord[i]);
                if (Util.isNotEmpty(dictionaryWords)){
                    wordBrokenIntoDictionaryWords.add(dictionaryWords);
                }

            }
        }
        if (wordBrokenIntoDictionaryWords.size() > 0){
            suggestions = this.getValidSuggestions(word, wordBrokenIntoDictionaryWords);
        }
        return suggestions;
    }

    private List<String> getValidSuggestions(final String word, final List<List<String>> wordBrokenIntoDictionaryWords) {
        List<String> suggestions;
        if (word.matches(AT_LEAST_ONE_DIGIT_REGEX)) {
            // Re-Write the word using letters and numbers.
            suggestions = Util.getCartesianProduct(wordBrokenIntoDictionaryWords).stream()
                    .filter(result -> result.length() == (word.length() + result.codePoints().filter(dash -> dash == '-').count()))
                    .collect(Collectors.toList());
        }
        else {
            suggestions = wordBrokenIntoDictionaryWords.stream()
                    .flatMap(Collection::stream)
                    .filter(result -> result.length() == (word.length() + result.codePoints().filter(dash -> dash == '-').count()))
                    .collect(Collectors.toList());
        }
        return suggestions;
    }

    private List<String> breakWordIntoDictionaryWords(final String word) {
        if (word.length() < 2 || dictionary.contains(word)) {
            return new ArrayList<>(Arrays.asList(word));
        }

        List<String> brokenIntoDictionaryWords = new ArrayList<>();
        Map<Integer, List<String>> allPossibleWords = getAllPossibleWordsInWord(word);
        if (allPossibleWords.size() == 0) {
            return brokenIntoDictionaryWords;
        }
        if (allPossibleWords.size() == 1) {
            return allPossibleWords.get(0);
        }
        // Re-write the original word using all possible words
        List<String> reWrittenWords = this.reWriteWordFromAllPossibleWords(word, allPossibleWords);
        brokenIntoDictionaryWords = reWrittenWords.stream()
                .filter(result -> result.length() == (word.length() + result.codePoints().filter(ch -> ch == '-').count()))
                .distinct()
                .collect(Collectors.toList());
        return brokenIntoDictionaryWords;
    }

    /**
     * Starting from first letter in word we look for all possible words within this word.
     * Example: CALLMENOW will return CALL, ALL, ME, MEN, NOW.
     * @param word
     * @return All possible words within a word.
     */
    private Map<Integer, List<String>> getAllPossibleWordsInWord(final String word) {
        Map<Integer, List<String>> possibleWords = new HashMap<>();
        List<String> wordsForThisIndex;
        for (int i = 0; i < word.length() ; i++) {
            for (int j = word.length(); j > i + 1; j--) {
                if (dictionary.contains(word.substring(i, j))) {
                    if (possibleWords.get(i) == null) {
                        wordsForThisIndex = new ArrayList<>();
                        wordsForThisIndex.add(word.substring(i, j));
                        possibleWords.put(i, wordsForThisIndex);
                    } else {
                        wordsForThisIndex = possibleWords.get(i);
                        wordsForThisIndex.add(word.substring(i, j));
                    }

                }
            }
        }
        return possibleWords;
    }

    /***
     * Uses cartesian product method
     * @param word
     * @param allPossibleWords
     * @return
     */
    private List<String> reWriteWordFromAllPossibleWords(final String word, final Map<Integer, List<String>> allPossibleWords) {
        List<String> base = new ArrayList<>();
        // Letter at first index must have a word starting with itself
        if (allPossibleWords.get(0) == null) {
            return base;
        }
        base.addAll(allPossibleWords.get(0));
        List<String> intermediate = new ArrayList<>();

        for (int i = 2; i < word.length(); i++) {
            if (allPossibleWords.get(i) != null) {
                for (String nextIndexWord : allPossibleWords.get(i)) {
                    for (String baseWord : base) {
                        if ((i + (baseWord.codePoints().filter(ch -> ch == '-').count()) == baseWord.length())) {
                            intermediate.add(baseWord.concat("-" + nextIndexWord));
                        }
                        else {
                            intermediate.add(baseWord);
                        }
                    }
                }
            }
            if (intermediate.size() > 0) {
                base.clear();
                base.addAll(intermediate);
                intermediate.clear();
            }
        }
        return base;
    }

}
