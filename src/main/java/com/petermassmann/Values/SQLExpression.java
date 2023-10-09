package com.petermassmann.Values;


import com.petermassmann.JSONParsable;

/**
 * A generic SQL expression. Useful for passing SQL functions and other in-built expressions.
 */
public class SQLExpression implements JSONParsable {

    private final String expression;

    public SQLExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String getJSON(boolean insideJSON) {
        return expression;
    }
}
