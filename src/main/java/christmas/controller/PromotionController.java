package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.PromotionManager;
import christmas.domain.VisitDate;
import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Optional;

public class PromotionController {

    private final InputView inputView;
    private final OutputView outputView;

    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.welcome();
        VisitDate visitDate = readVisitDate();
        Orders orders = readOrders();
        PromotionManager manager = new PromotionManager();
        manager.applyPromotionSale(visitDate, orders);
        manager.applyPresent(orders);
        Optional<Badge> badge = manager.applyBadge();

        outputView.printOrders(visitDate, orders);
        outputView.printPromotions(manager.getPresent(), manager.getPromotionHistory());
        outputView.printBenefitPrice(manager.totalBenefitPrice());
        outputView.printPay(orders.totalPrice() - manager.totalBenefitPrice());
        outputView.printBadge(badge);
    }

    private VisitDate readVisitDate() {
        return RetryHandler.runUntilSuccess(() -> {
            int date = inputView.readDate();

            return VisitDate.from(date);
        });
    }

    private Orders readOrders() {
        return RetryHandler.runUntilSuccess(() -> {
            List<Order> orders = Parser.toOrder(inputView.readOrder());

            return new Orders(orders);
        });
    }
}
