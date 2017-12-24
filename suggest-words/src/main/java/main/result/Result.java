package main.result;

import main.advice.AdviceService;
import main.encode.EncodeService;
import main.util.Util;
import java.util.List;


/**
 * Handles the printing of final result.
 * Calls the encode service and then advice service to print suggestions for a telephone number.
 */
public class Result {
    private String phoneNumber;
    private StringBuilder suggestion = new StringBuilder();

    public void showResults(final EncodeService encodeService, final AdviceService adviceService) {
        suggestion.append(phoneNumber).append(Util.COMMA_SEPARATOR);
        encodeService.encode(phoneNumber).forEach(encodedNumber -> printSuggestions(encodedNumber, adviceService));
        System.out.println(suggestion.toString());
    }

    private void printSuggestions(final String encodedNumber, AdviceService adviceService) {
        if (Util.isNotEmpty(encodedNumber)) {
            List<String> results = adviceService.getSuggestionsForWord(encodedNumber);
            if (results.size() > 0) {
                suggestion.append(results.toString());
            }
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringBuilder getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(StringBuilder suggestion) {
        this.suggestion = suggestion;
    }

}
