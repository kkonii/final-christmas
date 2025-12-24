package christmas.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum Badge {

    없음((benefit) -> benefit < 5000),
    별((benefit) -> benefit >= 5000 && benefit < 10000),
    트리((benefit) -> benefit >= 10000 && benefit < 20000),
    산타((benefit) -> benefit > 20000);

    private final Predicate<Integer> condition;

    private static final int STAR_MARGIN = 5000;
    private static final int TREE_MARGIN = 10000;
    private static final int SANTA_MARGIN = 20000;

    Badge(Predicate<Integer> condition) {
        this.condition = condition;
    }

    public static Optional<Badge> findBy(int benefitPrice) {
        return Arrays.stream(values())
                .filter(badge -> badge.condition.test(benefitPrice))
                .findAny()
                .or(Optional::empty);
    }
}
