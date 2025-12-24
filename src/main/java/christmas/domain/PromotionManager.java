package christmas.domain;

import christmas.domain.promotion.ChristmasPromotion;
import christmas.domain.promotion.Promotions;
import christmas.domain.promotion.SpecialPromotion;
import christmas.domain.promotion.WeekdayPromotion;
import christmas.domain.promotion.WeekendPromotion;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PromotionManager {

    private final Map<Promotions, Integer> promotionHistory;
    private final Map<Menu, Integer> present;

    public PromotionManager() {
        promotionHistory = new HashMap<>();
        present = new HashMap<>();
    }

    //프로모션 날짜 할인
    public Map<Promotions, Integer> applyPromotionSale(VisitDate visitDate, Orders orders) {
        if (orders.totalPrice() >= 10000) {
            promotionHistory.put(Promotions.CHRISTMAS, ChristmasPromotion.apply(visitDate));
            promotionHistory.put(Promotions.SPECIAL, SpecialPromotion.apply(visitDate, orders));
            promotionHistory.put(Promotions.WEEKDAY, WeekdayPromotion.apply(visitDate, orders));
            promotionHistory.put(Promotions.WEEKEND, WeekendPromotion.apply(visitDate, orders));
        }
        return promotionHistory;
    }

    //증정품 할인
    public Map<Menu, Integer> applyPresent(Orders orders) {
        if (orders.totalPrice() >= 120000) {
            present.put(Menu.샴페인, 1);
        }
        return present;
    }

    //뱃지
    public Optional<Badge> applyBadge() {
        int totalBenefit = presentBenefit() + saleBenefit();

        return Badge.findBy(totalBenefit);
    }

    //증정할인금
    private int presentBenefit() {
        if (present.isEmpty()) {
            return 0;
        }
        return present.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    //프로모션 할인금
    public int saleBenefit() {
        if (promotionHistory.isEmpty()) {
            return 0;
        }
        return promotionHistory.values()
                .stream()
                .mapToInt(integer -> integer)
                .sum();
    }

    //총혜택 받은 금액
    public int totalBenefitPrice() {
        return saleBenefit() + presentBenefit();
    }

    public Map<Menu, Integer> getPresent() {
        return present;
    }

    public Map<Promotions, Integer> getPromotionHistory() {
        return promotionHistory;
    }
}
