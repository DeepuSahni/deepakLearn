package main.dictionary;

import main.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Store user supplied and default dictionaries here
 */
public class DictionaryLoader {
    private static final String DEFAULT_DICTIONARY = "defaultDictionary.txt";
    private static final String DICTIONARY_OPTION = "dictionary";

    private DictionaryLoader() {
    }

    public static Set<String> getDictionary() {
        return new TreeSet(DictionaryLoader.loadUserDictionaryOrDefault().collect(Collectors.toSet()));
    }

    public static Stream<String> loadUserDictionaryOrDefault() {
        try {
            return Files.lines(Paths.get(Optional.ofNullable(System.getProperty(DICTIONARY_OPTION)).orElseGet(DictionaryLoader::getDefaultDictionaryPath)))
                    .map (word -> word.toUpperCase())
                    .distinct()
                    .map(word -> word.replaceAll(Util.UPPER_CASE_LETTER_REGEX, ""));
        } catch (IOException ex) {
            System.out.println("Error: Cannot find dictionary file." + ex.getMessage());
            return Stream.empty();
        }
    }

    public static String getDefaultDictionaryPath() {
            return DictionaryLoader.class.getClassLoader().getResource(DEFAULT_DICTIONARY).getPath();
    }

}
