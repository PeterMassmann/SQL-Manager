package com.github.PeterMassmann.Conditions;

import com.github.PeterMassmann.SQLManager;
import org.jetbrains.annotations.NotNull;

public class SQLJSONArrayCondition implements SQLCondition {

    private final String column;
    private final Object value;

    /**
     * A condition to check whether a column contains a specified value.
     * @param column The column to search in. Has to be a JSON array.
     * @param value The value to search.
     */
    public SQLJSONArrayCondition(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String getString(@NotNull SQLManager manager) {
        return "JSON_CONTAINS(" + column + ", '" + manager.parse(value, true) + "')";
    }
}
