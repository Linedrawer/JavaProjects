package org.petprojects.java.Shortener.strategies;

public interface StorageStrategy {

    //returns true if storage contained passed key
    boolean containsKey(Long key);

    // returns true is storage contains passed value
    boolean containsValue(String value);

    // put a new key-value
    void put(Long key, String value);

    //return key for passed value
    Long getKey(String value);

    //return value for passed key
    String getValue(Long key);
}
