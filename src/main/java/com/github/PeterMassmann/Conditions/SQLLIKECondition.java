package com.github.PeterMassmann.Conditions;

import com.github.PeterMassmann.SQLManager;

public class SQLLIKECondition implements SQLCondition {

    private final String column;
    private final boolean like;
    private final String pattern;

    /**
     * A condition to check whether a string column matches a specified pattern.
     * @param column The string column.
     * @param like Whether the string should match the pattern or not.
     * @param pattern The pattern to match.
     */
    public SQLLIKECondition(String column, boolean like, String pattern) {
        this.column = column;
        this.like = like;
        this.pattern = pattern;
    }

    @Override
    public String getString(SQLManager manager) {
        return column + (like ? " LIKE '" : " NOT LIKE '") + pattern + "'";
    }
}
