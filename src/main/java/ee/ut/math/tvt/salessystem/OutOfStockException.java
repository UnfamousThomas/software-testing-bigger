package ee.ut.math.tvt.salessystem;

public class OutOfStockException extends SalesSystemException {

    public OutOfStockException() {
        super();
    }

    public OutOfStockException(String message) {
        super(message);
    }
}
