package christmas.domain;

import christmas.exception.PromotionException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private final int date;
    private final DayOfWeek dayOfWeek;

    public VisitDate(int date, DayOfWeek dayOfWeek) {
        validateRange(date);
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public static VisitDate from(int date) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return new VisitDate(date, dayOfWeek);
    }

    //날짜가 1-31 밖일 때
    private void validateRange(int date) {
        if (date < 1 || date > 31) {
            throw new PromotionException("유효하지 않은 날짜입니다. 다시 입력해주세요.");
        }
    }

    public int getDate() {
        return date;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
