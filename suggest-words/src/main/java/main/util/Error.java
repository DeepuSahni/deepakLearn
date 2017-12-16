package main.util;

public enum Error {

    FILE_NOT_FOUND("Error: Cannot find file : "),
    BAD_PHONE_NUMBER("Error: Invalid phone number");
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
