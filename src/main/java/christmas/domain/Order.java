package christmas.domain;

import christmas.exception.PromotionException;

public class Order {

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
            throw new PromotionException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return menu.equals(order.menu);
    }

    @Override
    public int hashCode() {
        return menu.hashCode();
    }
}
