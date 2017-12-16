package main.read;

import main.util.Error;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {
    private InputStream inputStream;
    private Optional<Error> error = Optional.ofNullable(null);

    public InputReader() {
    }

    public InputReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Stream<String> readInput(final Stream<String> args) {
        List<Stream<String>> allNumbers = new ArrayList<>();
        args.forEach(arg -> this.readFileInput(arg, allNumbers));
        List<String> result = new ArrayList<>();

        if (allNumbers.size() > 0) {
            for (Stream<String> stream : allNumbers) {
                List<String> numbersInFile = stream.collect(Collectors.toList());
                result.addAll(numbersInFile);
            }
            return result.stream();
        }
        else if (this.getError().isPresent()) {
            return Stream.empty();
        }
        else {
            return this.readUserInput();
        }
    }

    public Stream<String> readUserInput() {
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
        List<String> userInput = new ArrayList<>();

        System.out.println(" ###### We are here to suggest! ######");
        System.out.println(" Please type a phone number and then hit return.");
        System.out.println(" OR exit this application and provide phone numbers in a file.");
        System.out.println(" You may supply multiple files for word suggestions.");
        System.out.println(" EXIT: Press \"CTRL + D\" to exit.");
        System.out.println(" #####################################");
        System.out.println();

        while (scanner.hasNext()) {
            userInput.add(scanner.nextLine());
        }
        return userInput.stream();
    }

    public void readFileInput(final String filePath, final List<Stream<String>> result) {
        try {
            result.add(Files.lines(Paths.get(filePath)));
        } catch (IOException ex) {
            this.setError(Optional.of(Error.FILE_NOT_FOUND));
            System.out.println(Error.FILE_NOT_FOUND.getText() + filePath);
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Optional<Error> getError() {
        return error;
    }

    public void setError(Optional<Error> error) {
        this.error = error;
    }
}
