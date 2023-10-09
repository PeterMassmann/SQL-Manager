package com.github.PeterMassmann.Conditions;

import com.github.PeterMassmann.SQLManager;
import org.jetbrains.annotations.NotNull;

public class SQLBETWEENCondition implements SQLCondition {

    private final String column;
    private final Object from;
    private final Object to;

    /**
     * A condition that checks whether a column lies between to values. Useful for dates.
     * @param column The column to check.
     * @param from The from-value.
     * @param to The to-value.
     */
    public SQLBETWEENCondition(String column, Object from, Object to) {
        this.column = column;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getString(@NotNull SQLManager manager) {
        return column + " BETWEEN " + manager.parse(from) + " AND " + manager.parse(to);
    }
}
