package main;

import main.interact.StandardInputReader;

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
        for (String arg : args) {
            System.out.println("Passed arguments: " + arg);
        }

    }
}
