package com.petermassmann;

/**
 * A generic parser interface that allows custom parsing of classes. This is especially useful when wanting to parse classes that you can't modify. For parsing of your own classes, see {@link JSONParsable}.
 * @param <T> The class this parser parses.
 */
public interface SQLClassParser<T> {

    String parse(T obj, boolean insideJSON);

}
