package com.petermassmann.Conditions;

import com.petermassmann.SQLManager;

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
