import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author John Choi
 * @author Sheen Brower
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public void testNoArgumentConstructor() {
        Map<String, String> myMap = this.createFromArgsTest();
        Map<String, String> oldMap = this.createFromArgsRef();

        assertEquals(myMap, oldMap);
    }

    @Test
    public void testArgumentConstructor1() {
        String key = "one";
        String value = "1";
        Map<String, String> myMap = this.createFromArgsTest(key, value);
        Map<String, String> oldMap = this.createFromArgsRef(key, value);

        assertEquals(myMap, oldMap);
    }

    @Test
    public void testArgumentConstructor2() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2);

        assertEquals(myMap, oldMap);
    }

    @Test
    public void testAddOnePair() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2);

        myMap.add("three", "3");
        oldMap.add("three", "3");
        assertEquals(myMap, oldMap);
    }

    @Test
    public void testAddMultiplePairs() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2);

        myMap.add("three", "3");
        oldMap.add("three", "3");
        myMap.add("four", "4");
        oldMap.add("four", "4");
        assertEquals(myMap, oldMap);
    }

    @Test
    public void testRemoveOnePair() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        String key3 = "three";
        String value3 = "3";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2, key3, value3);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2, key3, value3);

        myMap.remove("two");
        oldMap.remove("two");
        assertEquals(myMap, oldMap);
    }

    @Test
    public void testRemoveMultiplePairs() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        String key3 = "three";
        String value3 = "3";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2, key3, value3);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2, key3, value3);

        myMap.remove("two");
        oldMap.remove("two");
        myMap.remove("one");
        oldMap.remove("one");
        assertEquals(myMap, oldMap);
    }

    @Test
    public void testRemoveUntilEmpty() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        String key3 = "three";
        String value3 = "3";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2, key3, value3);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2, key3, value3);

        myMap.remove("two");
        myMap.remove("one");
        myMap.remove("three");
        oldMap.remove("one");
        oldMap.remove("two");
        oldMap.remove("three");
        assertEquals(myMap, oldMap);
    }

    @Test
    public void testRemoveAny() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        String key3 = "three";
        String value3 = "3";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2, key3, value3);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2, key3, value3);

        String removedKey = myMap.removeAny().key();
        oldMap.remove(removedKey);
        assertEquals(myMap, oldMap);
    }

    @Test
    public void testRemoveAnyUntilEmpty() {
        String key1 = "one";
        String value1 = "1";
        String key2 = "two";
        String value2 = "2";
        String key3 = "three";
        String value3 = "3";
        Map<String, String> myMap = this.createFromArgsTest(key1, value1, key2,
                value2, key3, value3);
        Map<String, String> oldMap = this.createFromArgsRef(key1, value1, key2,
                value2, key3, value3);

        // call removeAny three times from {@code myMap} and {@code oldMap}
        final int mapSize = 3;
        for (int i = 0; i < mapSize; i++) {
            myMap.removeAny();
            oldMap.removeAny();
        }
        assertEquals(myMap, oldMap);
    }

    @Test
    public void testValueThreePairs() {
        String[] keys = { "one", "two", "three" };
        String[] values = { "1", "2", "3" };
        Map<String, String> myMap = this.createFromArgsTest(keys[0], values[0],
                keys[1], values[1], keys[2], values[2]);

        // see if all keys call the correct value
        for (int i = 0; i < keys.length; i++) {
            assertEquals(values[i], myMap.value(keys[i]));
        }
    }

    @Test
    public void testHasKeyExistingAndNonExistingKeys() {
        String[] keys = { "one", "two", "three" };
        String[] values = { "1", "2", "3" };
        Map<String, String> myMap = this.createFromArgsTest(keys[0], values[0],
                keys[1], values[1], keys[2], values[2]);

        // make sure hasKey returns true for all keys in the map
        for (String key : keys) {
            assertTrue(myMap.hasKey(key));
        }

        // see if hasKey method in {@code myMap} returns false for non-existing keys
        String[] invalidKeys = { "alpha", "bravo", "charlie", "delta", "echo" };
        for (String invalidKey : invalidKeys) {
            assertFalse(myMap.hasKey(invalidKey));
        }
    }

    @Test
    public void testSizeEmptyMaps() {
        Map<String, String> myMap = this.createFromArgsTest();
        Map<String, String> oldMap = this.createFromArgsRef();

        assertEquals(0, myMap.size());
        assertEquals(oldMap.size(), myMap.size());
    }

    @Test
    public void testSizePopulatedMaps() {
        String[] keys = { "one", "two", "three" };
        String[] values = { "1", "2", "3" };
        Map<String, String> myMap = this.createFromArgsTest(keys[0], values[0],
                keys[1], values[1], keys[2], values[2]);
        Map<String, String> oldMap = this.createFromArgsRef(keys[0], values[0],
                keys[1], values[1], keys[2], values[2]);

        final int mapSize = 3;
        assertEquals(mapSize, oldMap.size());
        assertEquals(oldMap.size(), myMap.size());
    }

    @Test
    public void testSizeAddAndRemove() {
        String[] keys = { "one", "two", "three" };
        String[] values = { "1", "2", "3" };
        Map<String, String> myMap = this.createFromArgsTest(keys[0], values[0],
                keys[1], values[1], keys[2], values[2]);
        Map<String, String> oldMap = this.createFromArgsRef(keys[0], values[0],
                keys[1], values[1], keys[2], values[2]);

        // add pair and compare size again
        final int mapSize = 4;
        myMap.add("four", "4");
        oldMap.add("four", "4");
        assertEquals(mapSize, oldMap.size());
        assertEquals(oldMap.size(), myMap.size());

        // remove two pairs and compare size again
        myMap.remove("four");
        myMap.remove("two");
        oldMap.remove("four");
        oldMap.remove("two");
        assertEquals(2, oldMap.size());
        assertEquals(oldMap.size(), myMap.size());
    }

    /**
     * Tests For Constructor.
     */
    @Test
    public final void testConstructor() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        assertEquals(mapRef, mapTest);
    }

    /**
     * Tests For add.
     */
    @Test
    public final void testAdd1PairKIsZeroVIsZero() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        mapTest.add("Zero", "0");
        mapRef.add("Zero", "0");

        assertEquals(mapRef, mapTest);
    }

    @Test
    public final void testAdd2Pairs() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        mapTest.add("Zero", "0");
        mapRef.add("Zero", "0");

        mapTest.add("1", "1");
        mapRef.add("1", "1");

        assertEquals(mapRef, mapTest);
    }

    @Test
    public final void testAdd2PairsSizeCheck() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        mapTest.add("Zero", "0");
        mapRef.add("Zero", "0");

        mapTest.add("1", "1");
        mapRef.add("1", "1");

        assertEquals(mapRef, mapTest);
        assertEquals(mapRef.size(), mapTest.size());
    }

    @Test
    public final void testAdd2PairsSameValueDifferantKey() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        mapTest.add("Zero", "0");
        mapRef.add("Zero", "0");

        mapTest.add("One", "0");
        mapRef.add("One", "0");

        assertEquals(mapRef, mapTest);
    }

    /**
     * Tests For hasKey.
     */
    @Test
    public final void testHasKeyBothHaveKey() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0",
                "One", "1", "Two", "2");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0", "One",
                "1", "Two", "2");

        boolean bTest = mapTest.hasKey("Zero");
        boolean bRef = mapRef.hasKey("Zero");

        assertEquals(bRef, bTest);
        assertEquals(mapRef, mapTest);
    }

    @Test
    public final void testHasKeyBothDontHaveKeyEmpty() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        boolean bTest = mapTest.hasKey("Zero");
        boolean bRef = mapRef.hasKey("Zero");

        assertEquals(bRef, bTest);
        assertEquals(mapRef, mapTest);
    }

    @Test
    public final void testHasKeyBothDontHaveKey() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0",
                "Two", "2");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0", "Two",
                "2");

        boolean bTest = mapTest.hasKey("one");
        boolean bRef = mapRef.hasKey("one");

        assertEquals(bRef, bTest);
        assertEquals(mapRef, mapTest);
    }

    /**
     * Tests For remove().
     */
    @Test
    public final void testRemoveSetsNotEmptyAfterRemoveSizeFieldCheck() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        mapTest.add("Zero", "0");
        mapRef.add("Zero", "0");

        mapTest.add("1", "1");
        mapRef.add("1", "1");

        Pair<String, String> testRemoved = mapTest.remove("Zero");
        Pair<String, String> refRemoved = mapRef.remove("Zero");

        assertEquals(refRemoved, testRemoved);
        assertEquals(mapRef, mapTest);
        assertEquals(mapRef.size(), mapTest.size());
    }

    @Test
    public final void testRemoveSetsEmptyAfterRemoveSizeFieldCheck() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        mapTest.add("Zero", "0");
        mapRef.add("Zero", "0");

        Pair<String, String> testRemoved = mapTest.remove("Zero");
        Pair<String, String> refRemoved = mapRef.remove("Zero");

        assertEquals(refRemoved, testRemoved);
        assertEquals(mapRef, mapTest);
        assertEquals(mapRef.size(), mapTest.size());
    }

    /**
     * Tests For value().
     */
    @Test
    public final void testValue3elementsInSetSizeFieldCheck() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0",
                "One", "1", "Two", "2");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0", "One",
                "1", "Two", "2");
        String testsKey = mapTest.value("Zero");
        String refsKey = mapRef.value("Zero");

        assertEquals(mapRef, mapTest);
        assertEquals(refsKey, testsKey);
        assertEquals(mapRef.size(), mapTest.size());
    }

    @Test
    public final void testValue1ElementInSet() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0");
        String testsKey = mapTest.value("Zero");
        String refsKey = mapRef.value("Zero");

        assertEquals(mapRef, mapTest);
        assertEquals(refsKey, testsKey);
    }

    /**
     * Tests For removeAny().
     */

    @Test
    public final void testRemoveAnySetsNotEmptyAfterRemoveAnySizeFieldCheck() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0",
                "One", "1", "Two", "2");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0", "One",
                "1", "Two", "2");

        Pair<String, String> removedPair = mapTest.removeAny();
        String removedKey = removedPair.key();

        assertEquals(true, mapRef.hasKey(removedKey));

        mapRef.remove(removedKey);

        assertEquals(mapRef, mapTest);
        assertEquals(mapRef.size(), mapTest.size());
    }

    @Test
    public final void testRemoveAnySetsEmptyAfterRemoveAnySizeFieldCheck() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0");

        Pair<String, String> removedPair = mapTest.removeAny();
        String removedKey = removedPair.key();

        assertEquals(true, mapRef.hasKey(removedKey));

        mapRef.remove(removedKey);

        assertEquals(mapRef, mapTest);
        assertEquals(mapRef.size(), mapTest.size());
    }

    @Test
    public final void testRemoveAnySetsEmptyAfterRemoveAny() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0");

        Pair<String, String> removedPair = mapTest.removeAny();
        String removedKey = removedPair.key();

        assertEquals(true, mapRef.hasKey(removedKey));

        mapRef.remove(removedKey);

        assertEquals(mapRef, mapTest);
    }

    /**
     * Tests For size().
     */

    @Test
    public final void testSizeSetsNotEmpty() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0");

        int testSize = mapTest.size();
        int refSize = mapRef.size();

        assertEquals(mapRef, mapTest);
        assertEquals(refSize, testSize);
    }

    @Test
    public final void testSizeSetsIsEmpty() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        int testSize = mapTest.size();
        int refSize = mapRef.size();

        assertEquals(mapRef, mapTest);
        assertEquals(refSize, testSize);
    }

    @Test
    public final void testSizeSetsIsNotEmpty5Items() {
        Map<String, String> mapTest = this.createFromArgsTest("Zero", "0",
                "One", "1", "Two", "2", "Three", "3", "Four", "4");
        Map<String, String> mapRef = this.createFromArgsRef("Zero", "0", "One",
                "1", "Two", "2", "Three", "3", "Four", "4");

        int testSize = mapTest.size();
        int refSize = mapRef.size();

        assertEquals(mapRef, mapTest);
        assertEquals(refSize, testSize);
    }
}
