package com.github.PeterMassmann.Values;


import com.github.PeterMassmann.JSONParsable;
import com.github.PeterMassmann.SQLManager;

/**
 * A generic SQL expression. Useful for passing SQL functions and other in-built expressions.
 */
public class SQLExpression implements JSONParsable {

    private final String expression;

    public SQLExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String getJSON(SQLManager manager, boolean insideJSON) {
        return expression;
    }
}
