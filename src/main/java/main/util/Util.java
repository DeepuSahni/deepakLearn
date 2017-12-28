package main.util;

import java.util.List;

public class Util {
    private Util() {
    }

    public static final String NOT_NUMBER_REGEX = "[^0-9]+";
    public static final String NOT_AN_UPPER_CASE_REGEX = "[^A-Z]+";
    public static final String COMMA_SEPARATOR = " , ";
    public static final String MIN_ONE_DIGIT_REGEX = ".*[0-9]+.*";
    public static final String HAS_A_LETTER_REGEX = ".*[a-zA-Z]+.*";
    public static boolean isEmpty(String[] array){
        if (array !=null && array.length > 0) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(String[] array){
        return ! (isEmpty(array));
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
    public static boolean isEmpty(final List<String> list){
        if (list !=null && list.size() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(final List<String> list){
        return ! (isEmpty(list));
    }

}
