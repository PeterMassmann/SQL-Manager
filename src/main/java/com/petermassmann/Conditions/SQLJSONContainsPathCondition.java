package com.petermassmann.Conditions;

import com.petermassmann.SQLManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SQLJSONContainsPathCondition implements SQLCondition {

    public enum Quantity {
        ALL, ONE
    }

    private final String column;
    private final Quantity quantity;
    private final Set<String> paths;

    /**
     * A condition to check if a JSON object contains a specified path or set of paths.
     * @param column The column containing the JSON object.
     * @param quantity Whether one or all the specified paths must be present.
     * @param paths The path or set of paths to search.
     */
    public SQLJSONContainsPathCondition(
            String column,
            Quantity quantity,
            String... paths
    ) {
        this.column = column;
        this.quantity = quantity;
        this.paths = new HashSet<>(Arrays.asList(paths));
    }

    @Override
    public String getString(SQLManager manager) {
        return "JSON_CONTAINS_PATH(" + column + ", '" + quantity.toString().toLowerCase() + "', '" + String.join("', '", paths) + "')";
    }
}
