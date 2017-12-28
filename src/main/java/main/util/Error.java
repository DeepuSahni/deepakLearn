package main.util;

public enum Error {

    FILE_NOT_FOUND("Error: Cannot find file(s) : "),
    BAD_PHONE_NUMBER("Error: Invalid phone number: * Must have a digit * Must not have more than 10 digits * Must not have any letters."),
    DICTIONARY_NOT_FOUND(" Error: Dictionary Not Found."),
    BAD_FILE_NAME("Error: Invalid file name,."),
    FAILED_TO_CLOSE_STREAM("System Error : Failed to close stream. ");
    private String text;

    private Error(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
