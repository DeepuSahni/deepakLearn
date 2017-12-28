package main.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private Util() {
    }

    public static final String NOT_NUMBER_REGEX = "[^0-9]+";
    public static final String NOT_AN_UPPER_CASE_REGEX = "[^A-Z]+";
    public static final String COMMA_SEPARATOR = " , ";
    public static final String MIN_ONE_DIGIT_REGEX = ".*[0-9]+.*";
    public static final String HAS_A_LETTER_REGEX = ".*[a-zA-Z]+.*";

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

    public static boolean isListEmpty(final List<List<String>> list){
        if (list !=null && list.size() > 0) {
            return false;
        }
        return true;
    }

    public static List<String> getCartesianProduct(final List<List<String>> parentList ) {
        if (isListEmpty(parentList)) {
           return new ArrayList<>();
        }
        List<String> base = new ArrayList<>();
        base.addAll(parentList.get(0));

        List<String> intermediate = new ArrayList<>();
        for (List<String> remainingList : parentList.subList(1, parentList.size())) {
            for (int index = 0; remainingList != null && index < remainingList.size(); index++) {
                for (String baseMember : base) {
                    intermediate.add(new StringBuilder(baseMember).append(remainingList.get(index)).toString());
                }
            }
            if (intermediate.size() > 0) {
                base.clear();
                base.addAll(intermediate);
                intermediate.clear();
            }
        }
        return base;
    }
}
