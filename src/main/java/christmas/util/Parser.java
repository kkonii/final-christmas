package christmas.util;

import christmas.domain.Order;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static int toInteger(String value) {
        Validator.numeric(value);

        return Integer.parseInt(value);
    }

    //구분자로 파싱하여 주문 객체로 매핑
    public static List<Order> toOrder(String value) {
        return Arrays.stream(value.split(","))
                .map(token -> token.split("-"))
                .map(parts -> Order.of(parts[0], Parser.toInteger(parts[1])))
                .toList();
    }
}
