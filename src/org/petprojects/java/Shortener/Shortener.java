package org.petprojects.java.Shortener;


import org.petprojects.java.Shortener.strategies.StorageStrategy;

public class Shortener {

    //Field responsible for last key value which was passed while adding a new entry to storage
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;


    //constructor
    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }


    //methods

    //returns key by passed value
    public synchronized Long getId(String string){

        //Check whether exists passed value in storage
        if (storageStrategy.containsValue(string)) {
            // if positive -  return its key
            return storageStrategy.getKey(string);
        }
        //If storage does not contain passed value:
        else {
            lastId ++;
            //Add to the storage a new key-value entry (lastID - passed string)
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    public synchronized String getString(Long id) {

        if (storageStrategy.containsKey(id)) {
            return storageStrategy.getValue(id);
        }
        else {
            return null;
        }
    }
}
