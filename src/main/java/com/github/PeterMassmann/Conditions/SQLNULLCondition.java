package com.github.PeterMassmann.Conditions;

import com.github.PeterMassmann.SQLManager;

public class SQLNULLCondition implements SQLCondition {

    private final String column;
    private final boolean isNull;

    public SQLNULLCondition(String column, boolean isNull) {
        this.column = column;
        this.isNull = isNull;
    }

    @Override
    public String getString(SQLManager manager) {
        return column + " " + (isNull ? "IS NULL" : "IS NOT NULL");
    }
}
