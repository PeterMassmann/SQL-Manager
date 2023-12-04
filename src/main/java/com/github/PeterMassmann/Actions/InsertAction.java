package com.github.PeterMassmann.Actions;

import com.github.PeterMassmann.SQLManager;
import com.github.PeterMassmann.Values.SQLValue;
import com.github.PeterMassmann.Values.SQLValuesSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * An action to insert a row into a table.
 */
public class InsertAction {

    private final SQLManager manager;
    private final String tableName;
    private final SQLValuesSet values;

    /**
     *
     * @param manager The SQL manager that will be handling this operation.
     * @param tableName The table that the row will be added to.
     * @param values The values that will be added to the row.
     */
    public InsertAction(SQLManager manager, String tableName, SQLValuesSet values) {
        this.manager = manager;
        this.tableName = tableName;
        this.values = values;
    }

    /**
     * Add a value to be added in this row.
     * @param value The value pair to be added.
     */
    public void addValue(SQLValue value) {
        values.addValue(value);
    }

    /**
     * Execute this action and insert the specified values into the table.
     * @throws SQLException Thrown if an exception regarding the execution of the query is encountered.
     */
    public void execute() throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO ").append(tableName);
        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (SQLValue value : this.values.getValues()) {
            columns.add(value.getColumn());
            values.add(value.getValueString(manager));
        }

        query.append("(").append(String.join(",", columns)).append(")");
        query.append(" VALUES ").append("(").append(String.join(",", values)).append(")");

        Connection connection = manager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query.toString());
        statement.execute();
        statement.close();
        connection.close();
    }
}
