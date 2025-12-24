package christmas.domain.promotion;

import christmas.domain.Category;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.util.List;

public class WeekendPromotion {

    private static final List<DayOfWeek> WEEKENDS = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    public static int apply(VisitDate date, Orders orders) {
        if (WEEKENDS.contains(date.getDayOfWeek())) {
            return (int) orders.countOf(Category.메인) * 2023;
        }
        return 0;
    }
}
