package christmas.domain;

import christmas.exception.PromotionException;

public class Order implements Comparable<Order> {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        validateAmount(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order of(String menuName, int quantity) {
        Menu menu = Menu.findBy(menuName);
        return new Order(menu, quantity);
    }

    //수량이 1미만일때
    private void validateAmount(int quantity) {
        if (quantity < 1) {
            throw new PromotionException("유효하지 않은 수량입니다. 다시 입력해주세요.");
        }
    }

    //금액
    public int price() {
        return menu.getPrice() * quantity;
    }


    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(Order o) {
        return this.menu.compareTo(o.menu);
    }
}
