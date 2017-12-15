package main.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Store user supplied and default dictionaries here
 */
public class Dictionary {
    public static final String DEFAULT_DICTIONARY = "defaultDictionary.txt";
    public static final String DICTIONARY_OPTION = "dictionary";

    public static Stream<String> getDictionary() {
        try {
            return Files.lines(Paths.get(Optional.ofNullable(System.getProperty(DICTIONARY_OPTION)).orElseGet(Dictionary::getDefaultDictionaryPath)));
        } catch (IOException ex) {
            System.out.println("Error: Cannot find dictionary file." + ex.getMessage());
            return Stream.empty();
        }
    }

    public static String getDefaultDictionaryPath() {
            return Dictionary.class.getClassLoader().getResource(DEFAULT_DICTIONARY).getPath();
    }

}
