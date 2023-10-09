package com.github.PeterMassmann.Columns;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * A set of columns.
 */
public class SQLColumnSet {

    private final List<String> columns;

    /**
     *
     * @param columns The columns to be included in this set.
     */
    public SQLColumnSet(String... columns) {
        this.columns = Arrays.asList(columns);
    }

    public @NotNull String getString() {
        return String.join(", ", columns);
    }

    /**
     * Add a column to this set.
     * @param column The column to be added.
     */
    public void addColumn(String column) {
        columns.add(column);
    }
}
