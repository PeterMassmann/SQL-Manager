package com.petermassmann.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A set of ordering expressions.
 * @see SQLOrderExpression
 */
public class SQLOrderSet {

    private final List<SQLOrderExpression> orderExpressions = new ArrayList<>();

    /**
     *
     * @param expressions The order expressions to be included in this set.
     */
    public SQLOrderSet(SQLOrderExpression... expressions) {
        orderExpressions.addAll(Arrays.asList(expressions));
    }

    /**
     * Add an expression to this set.
     * @param expression The expression to be added.
     */
    public void addExpression(SQLOrderExpression expression) {
        orderExpressions.add(expression);
    }

    public String getString() {
        StringBuilder builder = new StringBuilder();
        List<String> expressions = new ArrayList<>();
        for (SQLOrderExpression expression : orderExpressions) {
            expressions.add(expression.getString());
        }
        builder.append(String.join(", ", expressions));
        return builder.toString();
    }

}
