package com.github.PeterMassmann.Conditions;

import com.github.PeterMassmann.SQLManager;

public class SQLNOTCondition implements SQLCondition {

    private final SQLCondition condition;

    public SQLNOTCondition(SQLCondition condition) {
        this.condition = condition;
    }

    @Override
    public String getString(SQLManager manager) {
        return "NOT " + condition.getString(manager);
    }
}
