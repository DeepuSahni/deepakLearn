package main.result;

import main.advice.AdviceService;
import main.encode.EncodeService;
import main.util.Error;
import main.util.Util;
import java.util.List;


/**
 * Handles the printing of final result.
 * Calls the encode service and then advice service to print suggestions for a telephone number.
 */
public class PhoneNumberProcessor {
    private String phoneNumber;
    private StringBuilder suggestion;

    public void processPhoneNumber(final EncodeService encodeService, final AdviceService adviceService) {
        suggestion = new StringBuilder();
        if (PhoneNumberSanitiser.getSanitisedPhoneNumber(phoneNumber).isPresent()) {
            suggestion.append(phoneNumber).append(Util.COMMA_SEPARATOR);
            encodeService.encode(phoneNumber).forEach(encodedNumber -> getSuggestions(encodedNumber, adviceService));
        }
        else {
            suggestion.append(Error.BAD_PHONE_NUMBER.getText());
        }
        System.out.println(suggestion.toString());
    }

    public void getSuggestions(final String encodedNumber, final AdviceService adviceService) {
            List<String> results = adviceService.getSuggestionsForWord(encodedNumber);
            if (results.size() > 0) {
                suggestion.append(results.toString());
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
