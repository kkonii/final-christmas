package christmas.domain.promotion;

import christmas.domain.VisitDate;

public class ChristmasPromotion {

    private static final int START = 1;
    private static final int END = 25;

    public static int apply(VisitDate date) {
        if (START <= date.getDate() && date.getDate() <= END) {
            return (date.getDate() - 1) * 100 + 1000;
        }
        return 0;
    }
}
