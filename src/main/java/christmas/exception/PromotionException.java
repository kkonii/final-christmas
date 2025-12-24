package christmas.exception;

public class PromotionException extends IllegalArgumentException {

    private static final String ERROR = "[ERROR] ";

    public PromotionException(String message) {
        super(ERROR + message);
    }
}
