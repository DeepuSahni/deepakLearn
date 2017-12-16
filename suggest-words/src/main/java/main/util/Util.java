package main.util;

public class Util {
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
}
