import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Sheen Brower
 * @authoer John Choi
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Test for Constructor
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /*
     * Tests for changeToExtractionMode
     */

    @Test
    public final void testExtractionMode_empty_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testExtractionMode_1_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        mExpected.add("green");
        m.add("green");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testExtractionMode_multiple_entries_check_no_fields_check() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "0",
                "1", "2", "3", "4", "5");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "0", "1", "2", "3", "4", "5");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testExtractionMode_multiple_entries_check_sizeField_insertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "0",
                "1", "2", "3", "4", "5");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "0", "1", "2", "3", "4", "5");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        boolean mInsertion = m.isInInsertionMode();
        boolean mExpInsertion = mExpected.isInInsertionMode();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
        assertEquals(mExpInsertion, mInsertion);
    }

    /*
     * Tests for add()
     */

    @Test
    public final void test_add1Entry_preExtraction() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("1");
        mExpected.add("1");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
    }

    @Test
    public final void test_add2Entries_sameValue_preExtraction() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("1");
        m.add("1");
        mExpected.add("1");
        mExpected.add("1");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
    }

    @Test
    public final void test_add2Entries_diffValue_preExtraction() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("1");
        mExpected.add("1");
        m.add("0");
        mExpected.add("0");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
    }

    //@Test
    public final void test_add1Entry_POST_Extraction() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        m.add("1");
        mExpected.add("1");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddBothEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        mExpected.add("green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /*
     * Tests for removeFirst
     */

    @Test
    public final void test_add1Entry_remove1() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("1");
        mExpected.add("1");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String mString = m.removeFirst();
        String mExpectedString = mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(mExpectedString, mString);
    }

    @Test
    public final void test_add12Entry_remove1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "0",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String mString = m.removeFirst();
        String mExpectedString = mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(mExpectedString, mString);
    }

    @Test
    public final void test_add12Entry_remove1_disorder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "11",
                "1", "7", "21", "4", "5", "6", "2", "8", "5", "10", "11");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "11", "1", "7", "21", "4", "5", "6", "2", "8", "5", "10", "11");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String mString = m.removeFirst();
        String mExpectedString = mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(mExpectedString, mString);
    }

    @Test
    public final void test_add12Entry_remove5_disorder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "11",
                "1", "7", "21", "4", "5", "6", "2", "8", "5", "10", "11");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "11", "1", "7", "21", "4", "5", "6", "2", "8", "5", "10", "11");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String mString = m.removeFirst();
        String mExpectedString = mExpected.removeFirst();
        String mString1 = m.removeFirst();
        String mExpectedString1 = mExpected.removeFirst();
        String mString2 = m.removeFirst();
        String mExpectedString2 = mExpected.removeFirst();
        String mString3 = m.removeFirst();
        String mExpectedString3 = mExpected.removeFirst();
        String mString4 = m.removeFirst();
        String mExpectedString4 = mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(mExpectedString, mString);
        assertEquals(mExpectedString1, mString1);
        assertEquals(mExpectedString2, mString2);
        assertEquals(mExpectedString3, mString3);
        assertEquals(mExpectedString4, mString4);
    }

    @Test
    public final void test_add3Duplicates_remove1() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("3");
        mExpected.add("3");
        m.add("3");
        mExpected.add("3");
        m.add("1");
        mExpected.add("1");
        m.add("3");
        mExpected.add("3");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String mString = m.removeFirst();
        String mExpectedString = mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(mExpectedString, mString);
    }

    @Test
    public final void test_add5Entry_remove5_disorder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "11",
                "1", "7", "21", "4");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "11", "1", "7", "21", "4");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String mString = m.removeFirst();
        String mExpectedString = mExpected.removeFirst();
        String mString1 = m.removeFirst();
        String mExpectedString1 = mExpected.removeFirst();
        String mString2 = m.removeFirst();
        String mExpectedString2 = mExpected.removeFirst();
        String mString3 = m.removeFirst();
        String mExpectedString3 = mExpected.removeFirst();
        String mString4 = m.removeFirst();
        String mExpectedString4 = mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(mExpectedString, mString);
        assertEquals(mExpectedString1, mString1);
        assertEquals(mExpectedString2, mString2);
        assertEquals(mExpectedString3, mString3);
        assertEquals(mExpectedString4, mString4);

    }

    @Test
    public final void test_add5Entry_remove5b_disorder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "2",
                "5", "6", "8", "5", "7");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "2", "5", "6", "8", "5", "7");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String mString = m.removeFirst();
        String mExpectedString = mExpected.removeFirst();
        String mString1 = m.removeFirst();
        String mExpectedString1 = mExpected.removeFirst();
        String mString2 = m.removeFirst();
        String mExpectedString2 = mExpected.removeFirst();
        String mString3 = m.removeFirst();
        String mExpectedString3 = mExpected.removeFirst();
        String mString4 = m.removeFirst();
        String mExpectedString4 = mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(mExpectedString, mString);
        assertEquals(mExpectedString1, mString1);
        assertEquals(mExpectedString2, mString2);
        assertEquals(mExpectedString3, mString3);
        assertEquals(mExpectedString4, mString4);
    }
    /*
     * Tests for isInInsertionMode
     */

    @Test
    public final void testInsertionBool_postChangeExtract_1entry() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        m.add("1");
        mExpected.add("1");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        boolean mInsertion = m.isInInsertionMode();
        boolean mExpecInsertion = mExpected.isInInsertionMode();

        assertEquals(mExpected, m);
        assertEquals(mExpecInsertion, mInsertion);
    }

    @Test
    public final void testInsertionBool_preChangeExtract_1entry() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        m.add("1");
        mExpected.add("1");
        boolean mInsertion = m.isInInsertionMode();
        boolean mExpecInsertion = mExpected.isInInsertionMode();

        assertEquals(mExpected, m);
        assertEquals(mExpecInsertion, mInsertion);
    }

    @Test
    public final void testInsertionBool_postChangeExtract_0entry() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        boolean mInsertion = m.isInInsertionMode();
        boolean mExpecInsertion = mExpected.isInInsertionMode();

        assertEquals(mExpected, m);
        assertEquals(mExpecInsertion, mInsertion);
    }

    @Test
    public final void testInsertionBool_preChangeExtract_0entry() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        boolean mInsertion = m.isInInsertionMode();
        boolean mExpecInsertion = mExpected.isInInsertionMode();

        assertEquals(mExpected, m);
        assertEquals(mExpecInsertion, mInsertion);
    }
    /*
     * Tests for size()
     */

    @Test
    public final void testsize_preExtraction_0_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpectedSize, mSize);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testsize_preExtraction_1_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("0");
        mExpected.add("0");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpectedSize, mSize);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testsize_preExtraction_3_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("8");
        mExpected.add("8");
        m.add("30");
        mExpected.add("30");
        m.add("1");
        mExpected.add("1");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpectedSize, mSize);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testsize_postExtraction_0_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
    }

    @Test
    public final void testsize_postExtraction_1_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("0");
        mExpected.add("0");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
    }

    @Test
    public final void testsize_postExtraction_3_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("8");
        mExpected.add("8");
        m.add("30");
        mExpected.add("30");
        m.add("1");
        mExpected.add("1");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
    }

    @Test
    public final void testsize_preAndPost_Extraction_0_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        int mSizePost = m.size();
        int mExpectedSizePost = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
        assertEquals(mExpectedSizePost, mSizePost);
    }

    @Test
    public final void testsize_preAndPost_Extraction_1_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        m.add("0");
        mExpected.add("0");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        int mSizePost = m.size();
        int mExpectedSizePost = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
        assertEquals(mExpectedSizePost, mSizePost);
    }

    @Test
    public final void testsize_preAndPost_Extraction_3_entries() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        m.add("8");
        mExpected.add("8");
        m.add("30");
        mExpected.add("30");
        m.add("1");
        mExpected.add("1");

        int mSize = m.size();
        int mExpectedSize = mExpected.size();

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        int mSizePost = m.size();
        int mExpectedSizePost = mExpected.size();

        assertEquals(mExpected, m);
        assertEquals(mExpectedSize, mSize);
        assertEquals(mExpectedSizePost, mSizePost);
    }

    /*
     * Tests for order()
     */
    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        Comparator<String> mOrder = m.order();
        Comparator<String> mExpectedOrder = mExpected.order();

        assertEquals(mExpected, m);
        assertEquals(mExpectedOrder, mOrder);
    }
}
