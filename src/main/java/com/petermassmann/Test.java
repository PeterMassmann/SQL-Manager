package com.petermassmann;

import com.petermassmann.Values.SQLValue;
import com.petermassmann.Values.SQLValuesSet;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        SQLManager manager = new SQLManager(
                "jdbc:mysql://urestoapi.cfnnxdqsbmnw.us-east-2.rds.amazonaws.com:3306/uresto",
                "admin",
                "urestoadmin"
        );

        manager.insert(
                "currencies",
                new SQLValuesSet(
                        new SQLValue("id", "ARS"),
                        new SQLValue("minimum_division", 1),
                        new SQLValue("symbol", "$")
                )
        ).execute();
    }
}
