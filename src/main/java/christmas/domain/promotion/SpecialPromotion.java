package christmas.domain.promotion;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.util.List;

public class SpecialPromotion {

    private static final List<Integer> VALID_DATES = List.of(3, 10, 17, 24, 25, 31);

    public static int apply(VisitDate date, Orders orders) {
        if (VALID_DATES.contains(date.getDate())) {
            return 1000;
        }
        return 0;
    }
}
