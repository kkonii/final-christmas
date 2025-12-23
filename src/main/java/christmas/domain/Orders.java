package christmas.domain;

import christmas.exception.PromotionException;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateTotalQuantity(orders);
        validateDuplicateMenu(orders);
        validateAllDrinks(orders);
        this.orders = orders;
    }

    //수량 20 초과
    private void validateTotalQuantity(List<Order> orders) {
        long quantities = orders.stream()
                .mapToInt(order -> order.getQuantity())
                .count();

        if (quantities > 20) {
            throw new PromotionException("수량 총합이 20개를 초과했습니다. 다시 입력해주세요.");
        }
    }

    //모두 음료만일 때
    private void validateAllDrinks(List<Order> orders) {
        boolean allDrinks = orders.stream()
                .allMatch(order -> order.getMenu().getCategory() == Category.음료);
        if (allDrinks) {
            throw new PromotionException("음료만 주문할 수 없습니다. 다시 입력해주세요.");
        }
    }

    //같은 메뉴 중복 입력
    private void validateDuplicateMenu(List<Order> orders) {
        List<Order> uniqueOrders = orders.stream()
                .distinct()
                .toList();

        if (orders.size() != uniqueOrders.size()) {
            throw new PromotionException("같은 메뉴가 중복으로 입력되었습니다. 다시 입력해주세요");
        }
    }

    public long countOf(Category category) {
        return orders.stream()
                .filter(order -> order.getMenu().getCategory() == category)
                .count();
    }

    //할인전 총액
    public int totalPrice() {
        return orders.stream()
                .mapToInt(Order::price)
                .sum();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
