package com.petermassmann.Conditions;

import com.petermassmann.SQLManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SQLANDConditionSet implements SQLCondition, SQLConditionSet {

    private final Set<SQLCondition> conditions;

    public SQLANDConditionSet(SQLCondition... conditions) {
        this.conditions = new HashSet<>(Arrays.asList(conditions));
    }

    public Set<SQLCondition> getConditions() {
        return conditions;
    }

    @Override
    public boolean isEmpty() {
        return conditions.isEmpty();
    }

    @Override
    public void addCondition(SQLCondition condition) {
        conditions.add(condition);
    }

    public String getString(SQLManager manager) {
        StringBuilder builder = new StringBuilder("(");

        int size = conditions.size();
        int counter = 1;
        for (SQLCondition condition : conditions) {
            builder.append(condition.getString(manager));

            if (counter < size) {
                builder.append(" AND ");
            }
            counter++;
        }

        builder.append(")");
        return builder.toString();
    }

    @Override
    public String getConditionSetString(SQLManager manager) {
        StringBuilder builder = new StringBuilder();

        int size = conditions.size();
        int counter = 1;
        for (SQLCondition condition : conditions) {
            builder.append(condition.getString(manager));

            if (counter < size) {
                builder.append(" AND ");
            }
            counter++;
        }

        return builder.toString();
    }
}
