package com.github.PeterMassmann.Actions;

import com.github.PeterMassmann.Conditions.SQLCondition;
import com.github.PeterMassmann.Conditions.SQLConditionSet;
import com.github.PeterMassmann.SQLManager;

import java.sql.SQLException;

/**
 * An action to delete rows from a table based on certain conditions.
 */
public class DeleteAction {

    private final SQLManager manager;
    private final String tableName;
    private final SQLConditionSet conditions;

    /**
     *
     * @param manager The SQL manager that will be handling this operation.
     * @param tableName The table this operation will affect.
     * @param conditions The conditions that a row has to meet in order to be deleted.
     */
    public DeleteAction(SQLManager manager, String tableName, SQLConditionSet conditions) {
        this.manager = manager;
        this.tableName = tableName;
        this.conditions = conditions;
    }

    /**
     * Add a condition to this action.
     * @param condition The condition to be added.
     */
    public void addCondition(SQLCondition condition) {
        conditions.addCondition(condition);
    }

    /**
     * Execute this action and delete the specified rows.
     * @throws SQLException Thrown if an exception regarding the execution of the query is encountered.
     */
    public void execute() throws SQLException {

        if (conditions.isEmpty()) {
            throw new SQLException("No condition was set.");
        }

        String query = "DELETE FROM " + tableName + " WHERE " + conditions.getConditionSetString(manager);

        manager.getConnection().prepareStatement(query).executeUpdate();
    }
}
