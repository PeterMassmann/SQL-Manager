package com.petermassmann.Order;

/**
 * An ordering expression that determine the order of rows.
 */
public class SQLOrderExpression {

    /**
     *
     * @param column The column that will determine the order.
     * @param order The order in which the column will be ordered, either ASC or DESC.
     */
    public SQLOrderExpression(String column, Order order) {
        this.column = column;
        this.order = order;
    }

    public enum Order {
        ASC, DESC
    }

    private final String column;
    private final Order order;

    public String getString() {
        return column + " " + order.toString();
    }

}
