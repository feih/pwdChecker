package pwd.storm;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordChecker {

    public static final String PASSED = "passed";
    private static final Pattern regex = Pattern.compile("([a-z0-9]+)\\1");

    /**
     * helper to check user password. It checks against the following rules:
     *
     * - consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
     * - be between 5 and 12 characters in length.
     * - not contain any sequence of characters immediately followed by the same sequence.
     *
     * @param rawPassowrd
     * @return result either "valid" or "invalid"
     */
    public String doTask(String rawPassowrd) {
        if (rawPassowrd == null || rawPassowrd.isEmpty()) {
            return "empty password";
        }

        // between 5 and 12 characters in length.
        if (rawPassowrd.length() < 5 || rawPassowrd.length() > 12) {
            return "password must be between 5-12 characters";
        }

        // mixture of lowercase letters and numerical digits only
        int lowerCaseCount = 0, numericCount = 0;
        for (int i = 0; i < rawPassowrd.length(); i++) {
            char aChar = rawPassowrd.charAt(i);

            if (aChar >= 'a' && aChar <= 'z') {
                lowerCaseCount++;
            }

            if (aChar >= '0' && aChar <= '9') {
                numericCount++;
            }
        }

        if (lowerCaseCount == 0 || numericCount == 0) {
            return "must have at least one for lowercase letters and numeric digit";
        }

        if (lowerCaseCount + numericCount < rawPassowrd.length()) {
            return "contains characters other than lower case letters and numeric digits";
        }

        // same sequence check - all the followings are considered "same sequence":
        // aa
        // aaa
        // abab  - negative case: abzab
        Matcher m = regex.matcher(rawPassowrd);
        if (m.find()) {
            return "repeated sub-sequence found";
        }

        return PASSED;
    }
}
