package com.petermassmann.Values;


import com.petermassmann.SQLManager;
import org.jetbrains.annotations.NotNull;

/**
 * A value pair that represents a column and a desired value for this column.
 */
public class SQLValue {

    private final String column;
    private final Object value;

    public SQLValue(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

    /**
     *
     * @param manager The SQL manager that will parse the value.
     * @return The string representation of the value.
     */
    public String getValueString(@NotNull SQLManager manager) {
        return manager.parse(value);
    }

    /**
     *
     * @param manager The SQL manager that will parse the value.
     * @return The string representation of the column-value pair.
     */

    public String getString(@NotNull SQLManager manager) {
        return column + " = " + manager.parse(value);
    }
}
