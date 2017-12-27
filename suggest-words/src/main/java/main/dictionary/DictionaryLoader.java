package main.dictionary;

import main.util.Error;
import main.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Sets up user supplied or default dictionary.
 */
public class DictionaryLoader {
    private static final String DEFAULT_DICTIONARY = "/defaultDictionary.txt";
    private static final String DICTIONARY_OPTION = "dictionary";

    private DictionaryLoader() {
    }

    /**
     * Load user dictionary if supplied otherwise load default default dictionary.
     * Convert all words to upper case and return unique non blank words only.
     * @return dictionary or empty set
     */
    public static Set<String> getDictionary() {
        Stream<String> lines = Stream.of("");
        Set<String> dictionary = new TreeSet<>();
        BufferedReader bufferedReader = null;
        try {
            if (System.getProperty(DICTIONARY_OPTION) != null) {
                lines = Files.lines(Paths.get(System.getProperty(DICTIONARY_OPTION)));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(DictionaryLoader.class.getResourceAsStream(DEFAULT_DICTIONARY)));
                lines = bufferedReader.lines();
            }
            dictionary = new TreeSet(lines.map(word -> word.toUpperCase()).distinct()
                    .map(word -> word.replaceAll(Util.NOT_AN_UPPER_CASE_REGEX, ""))
                    .filter(word -> word.length() > 0).collect(Collectors.toList()));
        } catch (IOException ex) {
            System.out.println(Error.DICTIONARY_NOT_FOUND.getText() + ex.getMessage());
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                lines.close();
            }
            catch (IOException ex) {
                System.out.println(Error.FAILED_TO_CLOSE_STREAM.getText());
            }
        }
        return dictionary;
    }
}
