package org.petprojects.java.Shortener;

import org.petprojects.java.Shortener.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Solution {

    //Method returns set of Ids depending on the passed set of strings
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> resultSet = new HashSet<>();

        for (String s : strings) {
            resultSet.add(shortener.getId(s));
        }
        return resultSet;
    }

    //Method returns set of strings (values) depending on the passed set of Ids (keys)
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> resultSet = new HashSet<>();

        for (Long l : keys) {
            resultSet.add(shortener.getString(l));
        }
        return resultSet;
    }

    //Method test passed strategy running it elementsNumber times
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {


        //Get strategy class name. Name does not contain package name
        Helper.printMessage(strategy.getClass().getSimpleName());

        //Generate a set of strings using Helper and its passed elementsNumber number
        Set<String> testSetStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetStrings.add(Helper.generateRandomString());
        }


        Shortener shortener = new Shortener(strategy);



        //Count and print time used for processing of getIds method for current strategy and passed number of elements.
        //Time is in ms.

        Set<Long> idsSet;

        Date startTime1 = new Date();
        idsSet = getIds(shortener, testSetStrings);
        Date finishTime1 = new Date();

        long msDelay1 = finishTime1.getTime() - startTime1.getTime();
        Helper.printMessage(Long.toString(msDelay1));


        //Count and print time used for processing of getStrings method for current strategy and passed set of ids.
        //Time is in ms.
        Set<String> stringSet;
        Date startTime2 = new Date();
        stringSet = getStrings(shortener, idsSet);
        Date finishTime2 = new Date();

        long msDelay2 = finishTime2.getTime() - startTime2.getTime();
        Helper.printMessage(Long.toString(msDelay2));



        //Compare wether the set of string is the same as was generated and set which was returned by getStrings method
        // If sets are equal test is passed
        if (testSetStrings.equals(stringSet)) {
            Helper.printMessage("Passed test.");
        } else {
            Helper.printMessage("Did not pass test.");
        }
    }

    public static void main(String[] args) {

        //check various strategies
        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);
        OurHashMapStorageStrategy strategy2 = new OurHashMapStorageStrategy();
        testStrategy(strategy2, 10000);
        FileStorageStrategy strategy3 = new FileStorageStrategy();
        testStrategy(strategy3, 500);
        OurHashBiMapStorageStrategy strategy4 = new OurHashBiMapStorageStrategy();
        testStrategy(strategy4, 10000);

    }

}
