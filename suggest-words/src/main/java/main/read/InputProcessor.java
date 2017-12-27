package main.read;

import main.advice.AdviceService;
import main.encode.EncodeService;
import main.result.PhoneNumberMapper;
import main.result.PhoneNumberSanitiser;
import main.util.Error;
import main.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * If valid files are supplied as arguments we will read from them.
 * If no file args is supplied then we will read from standard input.
 */
public class InputProcessor {
    private Optional<Error> error = Optional.ofNullable(null);

    public InputProcessor() {
    }

    public List<String> readFileInput(final Stream<String> args) {
        List<Stream<String>> allNumbers = new ArrayList<>();
        args.forEach(arg -> this.readFromFile(arg, allNumbers));
        List<String> result = new ArrayList<>();

        if (allNumbers.size() > 0) {
            for (Stream<String> stream : allNumbers) {
                List<String> numbersInFile = stream.collect(Collectors.toList());
                result.addAll(numbersInFile);
            }
        }
        return result;
    }

     public void processStandardInput(final EncodeService encode, final AdviceService advice, final InputStream inputStream)  {
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());

        System.out.println(" ###### We are here to suggest! ######");
        System.out.println(" Please type a phone number and then hit return.");
        System.out.println(" OR exit this application and provide phone numbers in a file, file names should be passed in as arguments.");
        System.out.println(" You may supply multiple files as arguments.");
         System.out.println(" Empty output will print when application cannot find any suggestions for the number.");
        System.out.println(" EXIT: Press \"CTRL + D\" to exit.");
        System.out.println(" #####################################");

        while (scanner.hasNext()) {
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.isEmpty()) {
                new PhoneNumberMapper().apply(phoneNumber).processPhoneNumber(encode, advice);
            }
        }
    }

    public void readFromFile(final String filePath, final List<Stream<String>> result) {
        try {
            result.add(Files.lines(Paths.get(filePath)));
        } catch (IOException ex) {
            this.setError(Optional.of(Error.FILE_NOT_FOUND));
            System.out.println(Error.FILE_NOT_FOUND.getText() + filePath);
        }
    }

    public Optional<Error> getError() {
        return error;
    }

    public void setError(Optional<Error> error) {
        this.error = error;
    }
}
