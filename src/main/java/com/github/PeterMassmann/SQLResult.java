package com.github.PeterMassmann;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLResult implements AutoCloseable {

    private final Connection connection;
    private final Statement statement;
    private final ResultSet set;

    public SQLResult(Connection connection, Statement statement, ResultSet set) {
        this.connection = connection;
        this.statement = statement;
        this.set = set;
    }

    // get result set
    public ResultSet getResultSet() {
        return this.set;
    }

    @Override
    public void close() throws SQLException {
        this.set.close();
        this.statement.close();
        this.connection.close();
    }
}
