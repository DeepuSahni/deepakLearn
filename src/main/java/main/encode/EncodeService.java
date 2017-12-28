package main.encode;

import main.util.Util;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Takes a phone number and encode the same into letters.
 * One phone number can have more than one encoded words for itself.
 * For example 225563 will have 700+ word encodings.
 */
public class EncodeService {
     private static final Map<String, String[]> encodingMap;

     static {
        encodingMap = new HashMap<>();
        encodingMap.put("2", new String[] {"A", "B", "C"});
        encodingMap.put("3", new String[] {"D", "E", "F"});
        encodingMap.put("4", new String[] {"G", "H", "I"});
        encodingMap.put("5", new String[] {"J", "K", "L"});
        encodingMap.put("6", new String[] {"M", "N", "O"});
        encodingMap.put("7", new String[] {"P", "Q", "R", "S"});
        encodingMap.put("8", new String[] {"T", "U", "V"});
        encodingMap.put("9", new String[] {"W", "X", "Y", "Z"});
    }

    public Stream<String> encode(final String phoneNumber) {
         if (Util.isBlank(phoneNumber)) {
             return Stream.empty();
         }
         List<List<String>> allDigitsMappedToLetters = this.mapDigitsToLetters(phoneNumber);

         if (allDigitsMappedToLetters == null) {
             return Stream.empty();
         }
         else if (allDigitsMappedToLetters.size() == 1) {
                 return allDigitsMappedToLetters.get(0).stream();
         }
         else if (allDigitsMappedToLetters.size() > 1) {
             return getCartesianProduct(allDigitsMappedToLetters).stream();
         }
        return Stream.empty();
    }

    private List<String> getCartesianProduct(final List<List<String>> parentList ) {
        List<String> base = new LinkedList<>();
        base.addAll(parentList.get(0));

        List<String> intermediate = new LinkedList<>();
        for (List<String> memberList : parentList.subList(1, parentList.size())) {
            for (int index = 0; memberList!=null && index < memberList.size(); index++) {
                for (String baseMember : base) {
                    intermediate.add(new StringBuilder(baseMember).append(memberList.get(index)).toString());
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


    /**
     * 1. For each digit of phone number, look into <code>encodingMap</code> to find matching letter set.
     * 2. If matching set of letters is found then add that to the result.
     * 3. If NO set of letter is found in then add that digit as it is to result
     * 4. NO two consecutive digits can remain unchanged.
     * @param phoneNumber
     * @return all digits changed to letters or null if 2 consecutive digits could not be changed.
     */
    private List<List<String>> mapDigitsToLetters(final String phoneNumber) {
        List<List<String>> allDigitsMappedToLetters = new LinkedList<>();
        boolean previousDigitIgnored = false;
        for (char digit : phoneNumber.toCharArray()) {
            String[] lettersForNumber = encodingMap.get(String.valueOf(digit));
            if (lettersForNumber != null) {
                previousDigitIgnored = false;
                allDigitsMappedToLetters.add(new LinkedList<>(Arrays.asList(lettersForNumber)));
            }
            else {
                if(previousDigitIgnored) {
                    return null;
                }
                previousDigitIgnored = true;
                // 1 digit can stay un-changed.
                allDigitsMappedToLetters.add(new LinkedList<>(Arrays.asList(String.valueOf(digit))));
            }
        }
        return allDigitsMappedToLetters;
    }
}
