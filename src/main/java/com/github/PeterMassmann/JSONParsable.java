package com.github.PeterMassmann;

/**
 * Defines a class that can be parsed into a SQL type or JSON string on a specific way.
 * <br>
 * Classes that implement this interface will automatically be parsed when passed as a value.
 */
public interface JSONParsable {

    /**
     *
     * @param insideJSON Whether the object is inside a JSON object or not.
     * @return The {@link String} representation of this object, either in JSON format or SQL format.
     */
    String getJSON(SQLManager manager, boolean insideJSON);

}
