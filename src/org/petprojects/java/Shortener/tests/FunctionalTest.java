package org.petprojects.java.Shortener.tests;

import org.junit.Assert;
import org.junit.Test;
import org.petprojects.java.Shortener.Helper;
import org.petprojects.java.Shortener.Shortener;
import org.petprojects.java.Shortener.strategies.*;

public class FunctionalTest {

    public void testStorage(Shortener shortener){

        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = s1;

        //get and save id of all three shorteners ids
        Long id1 = shortener.getId(s1);
        Long id2 = shortener.getId(s2);
        Long id3 = shortener.getId(s3);

        //Check that id2 is not equal to id1/id3
        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);

        //Check that id1 and id3 are equal
        Assert.assertEquals(id1, id3);

        //Get three values by id using shortener
        //Check that values are equal to the original ones
        Assert.assertEquals(s1, shortener.getString(id1));
        Assert.assertEquals(s2, shortener.getString(id2));
        Assert.assertEquals(s3, shortener.getString(id3));
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy()
    {
        StorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(ourHashMapStorageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy()
    {
        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(fileStorageStrategy);
        testStorage(shortener);
    }


    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(ourHashBiMapStorageStrategy);
        testStorage(shortener);
    }
}
