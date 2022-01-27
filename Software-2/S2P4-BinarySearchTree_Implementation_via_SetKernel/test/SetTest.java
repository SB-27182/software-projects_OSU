import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author John Choi
 * @author Sheen Brower
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Test Cases For constructor
     */
    // @Test
    public final void testSetOnBST_Constructor() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        assertEquals(ref, test);
    }

    /**
     * Test Cases For add
     */
    @Test
    public final void testSetOnBST_add_1_element() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        test.add("0");
        ref.add("0");

        assertEquals(ref, test);
    }

    @Test
    public final void testSetOnBST_add_2_elements_differant_values() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        test.add("0");
        test.add("1");
        ref.add("0");
        ref.add("1");

        assertEquals(ref, test);

    }

    @Test
    public final void testSetOnBST_add_10_elements() {
        Set<String> test = this.createFromArgsTest("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");
        Set<String> ref = this.createFromArgsRef("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");

        assertEquals(ref, test);

    }

    @Test
    public final void testSetOnBST_add_10_elements_differant_order_of_add() {
        Set<String> test = this.createFromArgsTest("9", "5", "7", "0", "3", "4",
                "1", "2", "6", "8");
        Set<String> ref = this.createFromArgsRef("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");

        assertEquals(ref, test);

    }

    /**
     * Test Cases For contains
     */
    @Test
    public final void testSetOnBST_contains_true() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        test.add("0");
        test.add("1");
        ref.add("0");
        ref.add("1");

        boolean testContains = test.contains("0");
        boolean refContains = ref.contains("0");

        assertEquals(refContains, testContains);
        assertEquals(ref, test);
    }

    @Test
    public final void testSetOnBST_contains_false_because_empty_sets() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        boolean testContains = test.contains("0");
        boolean refContains = ref.contains("0");

        assertEquals(refContains, testContains);
        assertEquals(ref, test);
    }

    @Test
    public final void testSetOnBST_contains_false_but_not_empty_sets() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        test.add("0");
        test.add("1");
        ref.add("0");
        ref.add("1");

        boolean testContains = test.contains("2");
        boolean refContains = ref.contains("2");

        assertEquals(refContains, testContains);
        assertEquals(ref, test);
    }

    /**
     * Test Cases For remove
     */

    @Test
    public final void testSetOnBST_remove_add1_remove1() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        test.add("0");
        ref.add("0");

        String testElem = test.remove("0");
        String refElem = ref.remove("0");

        assertEquals(ref, test);
        assertEquals(testElem, refElem);
    }

    @Test
    public final void testSetOnBST_remove_add10_remove1() {
        Set<String> test = this.createFromArgsTest("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");
        Set<String> ref = this.createFromArgsRef("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");

        String testElem = test.remove("0");
        String refElem = ref.remove("0");

        assertEquals(ref, test);
        assertEquals(testElem, refElem);
    }

    @Test
    public final void testSetOnBST_remove_add10_remove2() {
        Set<String> test = this.createFromArgsTest("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");
        Set<String> ref = this.createFromArgsRef("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");

        String testElem = test.remove("0");
        String testElem1 = test.remove("1");
        String refElem = ref.remove("0");
        String refElem1 = ref.remove("1");

        assertEquals(ref, test);
        assertEquals(refElem, testElem);
        assertEquals(refElem1, testElem1);
    }

    @Test
    public final void testSetOnBST_remove_add10_remove2_differant_order_add() {
        Set<String> test = this.createFromArgsTest("9", "5", "7", "0", "3", "4",
                "1", "2", "6", "8");
        Set<String> ref = this.createFromArgsRef("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");

        String testElem = test.remove("0");
        String testElem1 = test.remove("1");
        String refElem = ref.remove("0");
        String refElem1 = ref.remove("1");

        assertEquals(ref, test);
        assertEquals(refElem, testElem);
        assertEquals(refElem1, testElem1);
    }

    /**
     * Test Cases For removeAny
     */
    @Test
    public final void testSetOnBST_removeAny_1_element_set_of_10() {
        Set<String> testUnit = this.createFromArgsTest("0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");
        Set<String> refUnit = this.createFromArgsRef("0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");

        String testResult = testUnit.removeAny();

        assertEquals(true, refUnit.contains(testResult));
        refUnit.remove(testResult);

        assertEquals(refUnit, testUnit);
    }

    @Test
    public final void testSetOnBST_removeAny_1_element_set_of_10_sizeCheck() {
        Set<String> testUnit = this.createFromArgsTest("9", "5", "7", "0", "3",
                "4", "1", "2", "6", "8");
        Set<String> refUnit = this.createFromArgsRef("0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");

        String testResult = testUnit.removeAny();

        assertEquals(true, refUnit.contains(testResult));
        refUnit.remove(testResult);

        assertEquals(refUnit, testUnit);
        assertEquals(refUnit.size(), testUnit.size());
    }

    @Test
    public final void testSetOnBST_removeAny_1_element_set_of_1() {
        Set<String> testUnit = this.createFromArgsTest("0");
        Set<String> refUnit = this.createFromArgsRef("0");

        String testResult = testUnit.removeAny();

        assertEquals(true, refUnit.contains(testResult));

        refUnit.remove(testResult);

        assertEquals(refUnit, testUnit);
    }

    @Test
    public final void testSetOnBST_removeAny_1_element_set_of_1_sizeCheck() {
        Set<String> testUnit = this.createFromArgsTest("0");
        Set<String> refUnit = this.createFromArgsRef("0");

        String testResult = testUnit.removeAny();

        assertEquals(true, refUnit.contains(testResult));

        refUnit.remove(testResult);

        assertEquals(refUnit, testUnit);
        assertEquals(refUnit.size(), testUnit.size());
    }

    @Test
    public final void testSetOnBST_removeAny_2_elements_set_of_10() {
        Set<String> testUnit = this.createFromArgsTest("9", "5", "7", "0", "3",
                "4", "1", "2", "6", "8");
        Set<String> refUnit = this.createFromArgsRef("0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");

        String testResult1 = testUnit.removeAny();
        assertEquals(true, refUnit.contains(testResult1));
        refUnit.remove(testResult1);

        String testResult2 = testUnit.removeAny();
        assertEquals(true, refUnit.contains(testResult2));
        refUnit.remove(testResult2);

        assertEquals(refUnit, testUnit);
    }

    /**
     * Test Cases For size
     */

    @Test
    public final void testSetOnBST_size_0() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        int testUnitSize = test.size();
        int refUnitSize = ref.size();

        assertEquals(ref, test);
        assertEquals(refUnitSize, testUnitSize);
    }

    @Test
    public final void testSetOnBST_size_5() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        int testUnitSize = test.size();
        int refUnitSize = ref.size();

        assertEquals(ref, test);
        assertEquals(refUnitSize, testUnitSize);
    }

}
