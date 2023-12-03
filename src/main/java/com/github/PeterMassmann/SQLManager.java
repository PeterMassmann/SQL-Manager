package com.github.PeterMassmann;

import com.github.PeterMassmann.Actions.DeleteAction;
import com.github.PeterMassmann.Actions.InsertAction;
import com.github.PeterMassmann.Actions.SelectAction;
import com.github.PeterMassmann.Actions.UpdateAction;
import com.github.PeterMassmann.Columns.SQLColumnSet;
import com.github.PeterMassmann.Conditions.SQLConditionSet;
import com.github.PeterMassmann.Order.SQLOrderSet;
import com.github.PeterMassmann.Values.SQLValuesSet;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SQLManager {

    private static final BasicDataSource ds = new BasicDataSource();
    private final Map<Class<?>, SQLClassParser<?>> customParsers = new HashMap<>();

    public SQLManager(
            String url,
            String user,
            String password
    ) throws SQLException {
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setMinIdle(5);
        ds.setMaxIdle(25);
        ds.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public <T> void registerClassParser(Class<T> clazz, SQLClassParser<T> parser) {
        customParsers.put(clazz, parser);
    }

    public UpdateAction update(String tableName, SQLValuesSet values, SQLConditionSet conditions) {
        return new UpdateAction(this, tableName, values, conditions);
    }

    public InsertAction insert(String tableName, SQLValuesSet values) {
        return new InsertAction(this, tableName, values);
    }

    public SelectAction select(String tableName, SQLColumnSet columns, SQLConditionSet conditions) {
        return new SelectAction(this, tableName, columns, conditions, null);
    }

    public SelectAction select(String tableName, SQLColumnSet columns, SQLConditionSet conditions, SQLOrderSet order) {
        return new SelectAction(this, tableName, columns, conditions, order);
    }

    public DeleteAction delete(String tableName, SQLConditionSet conditions) {
        return new DeleteAction(this, tableName, conditions);
    }

    public <T> String parse(@Nullable T object, boolean insideJSON) {
        if (object != null && customParsers.containsKey(object.getClass())) {
            SQLClassParser<T> parser = (SQLClassParser<T>) customParsers.get(object.getClass());
            return parser.parse(object, insideJSON);
        } else if (object instanceof JSONParsable) {
            JSONParsable parsable = (JSONParsable) object;
            return parsable.getJSON(insideJSON);
        } else if (object instanceof UUID) {
            UUID uuid = (UUID) object;
            if (insideJSON) {
                return "\"" + uuid + "\"";
            }
            return "unhex(replace('" + uuid + "','-',''))";
        } else if (object instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) object;
            int size = collection.size();

            StringBuilder builder = new StringBuilder((insideJSON?"":"'") + "[");

            int counter = 1;
            for (Object obj : collection) {
                builder.append(parse(obj, true));
                if (counter < size) {
                    builder.append(", ");
                }
                counter++;
            }

            builder.append("]").append(insideJSON ? "" : "'");
            return builder.toString();
        } else if (object instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) object;

            int size = map.size();

            StringBuilder builder = new StringBuilder((insideJSON?"":"'") + "{");

            int counter = 1;
            for (Map.Entry<?,?> entry : map.entrySet()) {
                builder.append(parse(entry.getKey(), true)).append(": ").append(parse(entry.getValue(), true));
                if (counter < size) {
                    builder.append(", ");
                }
                counter++;
            }

            builder.append("}").append(insideJSON ? "" : "'");
            return builder.toString();
        } else if (object instanceof String) {
            return (insideJSON?"\"":"'") + object + (insideJSON?"\"":"'");
        } else if (object == null) {
            return (insideJSON?"{}":"NULL");
        }
        return object.toString();
    }

    public <T> String parse(@Nullable T object) {
        return parse(object, false);
    }

}
