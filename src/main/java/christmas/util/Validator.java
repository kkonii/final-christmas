package christmas.util;

import christmas.exception.PromotionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NUMERIC = Pattern.compile("^[+-]?\\d+$");

    public static void numeric(String value) {
        Matcher matcher = NUMERIC.matcher(value);

        if (!matcher.matches()) {
            throw new PromotionException("숫자만 입력할 수 있습니다. 다시 입력해주세요.");
        }
    }
}
