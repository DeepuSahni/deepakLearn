package main.parse;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * Parse/Validate user passed arguments
 * Arguments are fileNames that contain telephone numbers
 * */
public class ArgumentParser {
    private static final String FILE_REGEX = ".*[_A-Za-z0-9]+.*";
    private static final Predicate<String> VALID_ARG = argument -> isValidArg(argument);

    ArgumentParser() {
    }

    public static Stream<String> parseArguments(final String[] args) {
        if (args == null) {
            return Stream.empty();
        }
        return Arrays.stream(args).map(String::trim).filter(VALID_ARG);
    }

    public static boolean  isValidArg(String arg) {
        if (arg.matches(FILE_REGEX)) {
            return true;
        }
        return false;
    }

}
