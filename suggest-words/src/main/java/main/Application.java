package main;

import main.advice.AdviceService;
import main.dictionary.DictionaryLoader;
import main.encode.EncodeService;
import main.parse.ArgumentParser;
import main.read.InputReader;
import main.result.ResultMapper;
import main.util.Error;
import main.util.Util;

import java.util.List;
import java.util.Set;

/**
 * Entry point of application
 *
 1. Parse: The arguments (user can enter ZERO args).

 2. Load dictionary: Use default if no dictionary file specified by user.

 3. Interactive mode:  If no args given (Use Scanner to read from STDIN).

 4. Encode: Read the phone number and encode it into letters.

 5. Advise: Take encoded output and find matches in the dictionary.

 6. Results: Prepare the results and show to the user.

 7. Repeat: If this is interactive mode.
 **/
public class Application {

    public static void main(String[] args) {
        new Application().startApplication(args);
    }

    public void startApplication(String[] args) {
        Set<String> dictionary = DictionaryLoader.getDictionary();
        if (dictionary.size() < 1) {
            System.out.println(Error.DICTIONARY_NOT_FOUND.getText());
        }
        else {
            runApplication(args, dictionary);
        }
    }


    public void runApplication(final String[] args, final  Set<String> dictionary) {
        AdviceService advice = new AdviceService(dictionary);
        EncodeService encode = new EncodeService();
        InputReader inputReader = new InputReader();
        List<String> numbersInFiles = inputReader.readFileInput(ArgumentParser.parseArguments(args));

        if (Util.isNotEmpty(numbersInFiles)) {
            numbersInFiles.stream()
                    .filter(number -> number.matches(Util.PHONE_NUMBER_REGEX))
                    .map(new ResultMapper())
                    .forEach(result -> result.showResults(encode, advice));
        }
        else if (!inputReader.getError().isPresent()){
            inputReader.processStandardInput(encode, advice, System.in);
        }

    }
}