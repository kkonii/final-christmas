package christmas.domain.promotion;

import christmas.domain.Category;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.util.List;

public class WeekdayPromotion {

    private static final List<DayOfWeek> WEEKDAYS = List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);

    public static int apply(VisitDate date, Orders orders) {
        if (WEEKDAYS.contains(date.getDayOfWeek())) {
            //디저트 2023
            return (int) orders.countOf(Category.디저트) * 2023;
        }
        return 0;
    }
}
