package com.github.PeterMassmann.Conditions;

import com.github.PeterMassmann.SQLManager;

public class SQLOperatorCondition implements SQLCondition {

    private final String column;
    private final Object value;
    private final String operator;

    public SQLOperatorCondition(String column, String operator, Object value) {
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public String getString(SQLManager manager) {
        return column + " " + operator + " " + manager.parse(value);
    }
}
