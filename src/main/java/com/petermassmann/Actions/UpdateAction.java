package com.petermassmann.Actions;

import com.petermassmann.Conditions.SQLCondition;
import com.petermassmann.Conditions.SQLConditionSet;
import com.petermassmann.SQLManager;
import com.petermassmann.Values.SQLValue;
import com.petermassmann.Values.SQLValuesSet;

import java.sql.SQLException;

/**
 * An action to update all rows in a table that match certain conditions.
 */
public class UpdateAction {

    private final SQLManager manager;
    private final String tableName;
    private final SQLValuesSet values;
    private final SQLConditionSet conditions;

    /**
     *
     * @param manager The SQL manager that will be handling this operation.
     * @param tableName The table that will be affected.
     * @param values The value pairs that will be updated within each updated row.
     * @param conditions The condition that a row has to meet to be updated.
     */
    public UpdateAction(SQLManager manager, String tableName, SQLValuesSet values, SQLConditionSet conditions) {
        this.manager = manager;
        this.tableName = tableName;
        this.values = values;
        this.conditions = conditions;
    }

    /**
     * Add a value pair to be updated within the row.
     * @param value The value to be added.
     */
    public void addValue(SQLValue value) {
        values.addValue(value);
    }

    /**
     * Add a condition a row has to meet in order to be updated.
     * @param condition The condition to be added.
     */
    public void addCondition(SQLCondition condition) {
        conditions.addCondition(condition);
    }

    /**
     * Execute this action and update all rows matching the conditions.
     * @throws SQLException Thrown if an exception regarding the execution of the query is encountered.
     */
    public void execute() throws SQLException {

        if (conditions.isEmpty()) {
            throw new SQLException("No condition was set.");
        }

        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(tableName).append(" SET ");

        int size = values.getValues().size();
        int counter = 1;
        for (SQLValue value : values.getValues()) {
            query.append(value.getString(manager));
            if (counter < size) {
                query.append(", ");
            }
            counter++;
        }

        query.append(" WHERE ").append(conditions.getConditionSetString(manager));
        manager.getConnection().prepareStatement(query.toString()).executeUpdate();
    }

}
