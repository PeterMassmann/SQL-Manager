package com.petermassmann.Conditions;

import com.petermassmann.SQLManager;

import java.util.Collection;

public class SQLINCondition<K> implements SQLCondition {

    private final String column;
    private final Collection<K> values;
    private final boolean contained;

    /**
     * A condition that checks whether a column is contained within a set of values.
     * @param column The column to check.
     * @param values The values to check in.
     * @param contained Whether the column should be contained within the values or not.
     */
    public SQLINCondition(String column, Collection<K> values, boolean contained) {
        this.column = column;
        this.values = values;
        this.contained = contained;
    }

    @Override
    public String getString(SQLManager manager) {
        StringBuilder builder = new StringBuilder();
        builder.append(column).append((contained?" IN ":" NOT IN ")).append("(");
        int counter = 0;
        for (K value : values) {
            builder.append(manager.parse(value));
            if (counter <  values.size() - 1) {
                builder.append(", ");
            }
            counter++;
        }
        builder.append(")");
        return builder.toString();
    }

}
