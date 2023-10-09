package com.github.PeterMassmann.Values;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A set of column-value pairs.
 */
public class SQLValuesSet {

    private final Set<SQLValue> values;

    /**
     *
     * @param values The column-value pairs to be included in this set.
     */
    public SQLValuesSet(SQLValue... values) {
        this.values = new HashSet<>(Arrays.asList(values));
    }

    public Set<SQLValue> getValues() {
        return values;
    }

    /**
     * Add a column-value pair to this set.
     * @param value The pair to be added.
     */
    public void addValue(SQLValue value) {
        values.add(value);
    }
}
