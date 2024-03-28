package ee.ut.math.tvt.salessystem.logic;

import ee.ut.math.tvt.salessystem.SalesSystemException;

public class InvalidNumberException extends SalesSystemException {
    public InvalidNumberException(int num) {
        super("Inserted number is invalid for that: " + num);
    }

    public InvalidNumberException(double num) {
        super("Inserted number is invalid for that: " + num);

    }
}
