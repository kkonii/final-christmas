package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.promotion.Promotions;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    public void printOrders(VisitDate visitDate, Orders orders) {
        print(visitDate);
        printOrder(orders);
        printTotalOrderedPrice(orders);
    }

    public void print(VisitDate date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date.getDate());
        System.out.println();
        System.out.println();
    }

    private void printOrder(Orders orders) {
        System.out.println("<주문 메뉴>");
        for (Order order : orders.getOrders()) {
            System.out.printf("%s %d개", order.getMenu().name(), order.getQuantity());
            System.out.println();
        }
        System.out.println();
    }

    private void printTotalOrderedPrice(Orders orders) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원", orders.totalPrice());
        System.out.println();
    }

    //혜택 내역들
    public void printPromotions(Map<Menu, Integer> present, Map<Promotions, Integer> promotions) {
        printPresent(present);
        printBenefits(promotions);
    }

    public void printPresent(Map<Menu, Integer> present) {
        System.out.println("<증정 메뉴>");
        if (present.isEmpty()) {
            System.out.println("없음");
            return;
        }
        present.keySet().forEach(key -> {
            System.out.printf("%s %d개", key.name(), present.get(key));
            System.out.println();
        });
    }

    public void printBenefits(Map<Promotions, Integer> promotions) {
        System.out.println("<혜택 내역>");
        if (promotions.isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        for (Promotions promotion : promotions.keySet()) {
            if (promotions.get(promotion) != 0) {
                System.out.printf("%s: -%,d원", promotion.getTitle(), promotions.get(promotion));
                System.out.println();
            }
        }
        System.out.println();
    }

    public void printBenefitPrice(int price) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원", price);
        System.out.println();
        System.out.println();
    }

    public void printPay(int price) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원", price);
        System.out.println();
        System.out.println();
    }

    public void printBadge(Optional<Badge> badge) {
        System.out.println("<12월 이벤트 배지>");
        if (badge.isEmpty()) {
            System.out.println("없음");
            return;
        }
        badge.ifPresent(System.out::println);
    }
}
