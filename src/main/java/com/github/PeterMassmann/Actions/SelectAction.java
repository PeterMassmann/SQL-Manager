package com.github.PeterMassmann.Actions;

import com.github.PeterMassmann.Conditions.SQLCondition;
import com.github.PeterMassmann.Conditions.SQLConditionSet;
import com.github.PeterMassmann.Columns.SQLColumnSet;
import com.github.PeterMassmann.Order.SQLOrderSet;
import com.github.PeterMassmann.SQLManager;
import com.github.PeterMassmann.SQLResult;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An action to retrieve all rows matching a set of conditions and other parameters.
 */
public class SelectAction {

    private final SQLManager manager;
    private final String tableName;
    private final SQLColumnSet columns;
    private final SQLConditionSet conditions;
    private final StringBuilder additionalText;
    private final SQLOrderSet order;
    private Integer limit = null;
    private Integer offset = null;

    /**
     *
     * @param manager The SQL manager that will be handling this operation.
     * @param tableName The table from which the rows will be retrieved from.
     * @param columns The columns that will be retrieved.
     * @param conditions The conditions that need to be met by a row to be selected.
     * @param order An order expression or set of expressions that will determine the order of the selected rows. Can be null.
     */
    public SelectAction(SQLManager manager, String tableName, SQLColumnSet columns, SQLConditionSet conditions, @Nullable SQLOrderSet order) {
        this.manager = manager;
        this.tableName = tableName;
        this.columns = columns;
        this.conditions = conditions;
        this.order = order;
        this.additionalText = new StringBuilder();
    }

    /**
     * Add a condition that has to be met by the rows.
     * @param condition The condition to be added.
     */
    public void addCondition(SQLCondition condition) {
        this.conditions.addCondition(condition);
    }

    /**
     * Add a column that should be retrieved.
     * @param column The column to be added.
     */
    public void addColumn(String column) {
        this.columns.addColumn(column);
    }

    /**
     * Add any additional text to be added the SQL query. This text will be added at the end of the query.
     * <br>
     * Most times the text has to start with a space.
     * @param text The text to be added.
     * @return The same instance, for chaining.
     */
    public SelectAction addText(String text) {
        additionalText.append(text);
        return this;
    }

    /**
     * Set the return limit of this operation. By default, there is no limit.
     * @param limit The new limit.
     * @return The same instance, for chaining.
     */
    public SelectAction setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Set the return offset of this operation. The offset determines how many rows are skipped at the beginning. By default, there is no offset.
     * @param offset The new offset.
     * @return The same instance, for chaining.
     */
    public SelectAction setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Retrieve all rows in the table that meet all conditions.
     * @return A {@link SQLResult} containing all retrieved rows.
     * @throws SQLException Thrown if an exception regarding the execution of the query is encountered.
     */
    public SQLResult retrieve() throws SQLException {
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(columns.getString()).append(" FROM ").append(tableName);
        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(conditions.getConditionSetString(manager));
        }

        if (order != null) {
            query.append(" ORDER BY ").append(order.getString());
        }

        query.append(additionalText);
        try {
            Connection connection = manager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query.toString());
            return new SQLResult(connection, statement, set);
        } catch (SQLException e) {
            throw new SQLException(query.toString());
        }
    }
}
