package main.result;

import java.util.function.Function;

/**
 * Mapper class for converting a phone number into Result object.
 */
public class ResultMapper implements Function<String, Result>{

    @Override
    public Result apply(final String phoneNumber) {
        Result result = new Result();
        result.setPhoneNumber(phoneNumber);
        return result;
    }

}
