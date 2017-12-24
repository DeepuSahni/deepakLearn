package main.parse;

import main.util.Error;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * Parse/Validate user passed arguments
 * Checks if passed in argument is a valid file name or not.
 * */
public class ArgumentParser {
    private static final String FILE_REGEX = ".*[_A-Za-z0-9]+.*";
    private static final Predicate<String> VALID_FILE_NAME = argument -> isValidFileName(argument);

    ArgumentParser() {
    }

    public static Stream<String> parseArguments(final String[] args) {
        if (args == null) {
            return Stream.empty();
        }
        return Arrays.stream(args).map(String::trim).filter(VALID_FILE_NAME);
    }

    public static boolean isValidFileName(String arg) {
        if (arg.matches(FILE_REGEX)) {
            return true;
        }
        System.out.println(Error.BAD_FILE_NAME.getText());
        return false;
    }

}
