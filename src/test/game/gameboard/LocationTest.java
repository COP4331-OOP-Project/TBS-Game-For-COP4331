package game.gameboard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by David on 2/4/2017.
 */
public class LocationTest {
    public Set<Location> locations;
    public HashMap<Location, Integer> hashMap;

    @Before
    public void setUp(){
        locations = new HashSet<Location>();
        hashMap = new HashMap<Location,Integer>();
    }

    @Test
    public void TestHashCodeSet(){
        locations.add(new Location(0,0));
        locations.add(new Location(0,0));
        locations.add(new Location(0,0));
        locations.add(new Location(0,0));
        locations.add(new Location(2,0));
        locations.add(new Location(0,2));
        locations.add(new Location(2,2));

        Assert.assertEquals(locations.contains(new Location(0,0)), true);
        Assert.assertEquals(locations.contains(new Location(0,3)), false);
        Assert.assertEquals(locations.size(),4);
    }

    @Test
     public void TestHashCodeHashMap(){
        hashMap.put(new Location(0,0),2);
        hashMap.put(new Location(3,0),2);
        hashMap.put(new Location(0,0),1);
        hashMap.put(new Location(0,0),2);
        hashMap.put(new Location(0,0),3);
        Assert.assertEquals(hashMap.size(),2);
        Assert.assertEquals(hashMap.containsKey(new Location(0,0)),true);
        Assert.assertEquals(hashMap.containsKey(new Location(1,1)),false);
        Assert.assertEquals((int)hashMap.get(new Location(0,0)),3);
    }

}
