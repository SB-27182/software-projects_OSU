import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Sheen Brower
 * @author John Choi
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test NaturalNumber constructor and method with no arguments.
     */

    @Test
    public final void testNoArgumentConstructor() {
        NaturalNumber nTest = this.constructorTest();
        NaturalNumber nRef = this.constructorRef();

        assertEquals(nRef, nTest);
    }

    /*
     * Several Tests for NaturalNumber constructor with String argument.
     */

    @Test
    public final void testStringArgumentConstructor_1() {
        String s = "1";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        assertEquals(nRef, nTest);
    }

    @Test
    public final void testStringArgumentConstructor5() {
        String s = "0999";
        try {
            NaturalNumber n = this.constructorTest(s);
            NaturalNumber nT = this.constructorRef(s);
            assertEquals(n, nT);
            fail();
        } catch (
            Error e
        ) {
            assertEquals(
                    "Violation of: there exists n: NATURAL (s = TO_STRING(n))",
                    e.getMessage());
        }
    }

    @Test
    public final void testString_ArgumentConstructor_larger_than_long() {
        NaturalNumber testObj = this.constructorRef(Long.MAX_VALUE + "0");
        NaturalNumber refObj = this.constructorTest(Long.MAX_VALUE + "0");

        assertEquals(refObj, testObj);

    }

    @Test
    public final void testStringArgumentConstructorZERO() {
        String s = "0";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        assertEquals(nRef, nTest);
    }

    /*
     * Several Tests for NaturalNumber constructor with int argument.
     */

    @Test
    public final void testIntArgumentConstructor_1() {
        int i = 1;
        NaturalNumber nTest = this.constructorTest(i);
        NaturalNumber nRef = this.constructorRef(i);

        assertEquals(nRef, nTest);
    }

    @Test
    public final void testIntArgumentConstructor_Max_Integer() {
        int i = Integer.MAX_VALUE;
        NaturalNumber nTest = this.constructorTest(i);
        NaturalNumber nRef = this.constructorRef(i);

        assertEquals(nRef, nTest);
    }

    @Test
    public final void testIntArgumentConstructorZERO() {
        int i = 0;
        NaturalNumber nTest = this.constructorTest(i);
        NaturalNumber nRef = this.constructorRef(i);

        assertEquals(nRef, nTest);
    }

    /*
     * Several Tests for NaturalNumber constructor with NN argument.
     */

    @Test
    public final void testNNArgumentConstructor_larger_than_long() {
        NaturalNumber testParam = this.constructorRef(Long.MAX_VALUE + "0");
        NaturalNumber refParam = this.constructorRef(Long.MAX_VALUE + "0");

        NaturalNumber testObj = this.constructorTest(testParam);
        NaturalNumber refObj = this.constructorRef(refParam);

        assertEquals(refParam, testParam);
        assertEquals(refObj, testObj);

    }

    @Test
    public final void testNNArgumentConstructor_zero_parameter_value() {
        NaturalNumber testParam = this.constructorRef();
        NaturalNumber refParam = this.constructorRef();

        NaturalNumber testObj = this.constructorTest(testParam);
        NaturalNumber refObj = this.constructorRef(refParam);

        assertEquals(refParam, testParam);
        assertEquals(refObj, testObj);

    }

    @Test
    public final void testNNArgumentConstructor_parameter1() {
        NaturalNumber testParam = this.constructorRef("1");
        NaturalNumber refParam = this.constructorRef("1");

        NaturalNumber testObj = this.constructorTest(testParam);
        NaturalNumber refObj = this.constructorRef(refParam);

        assertEquals(refParam, testParam);
        assertEquals(refObj, testObj);

    }

    /*
     * Several Tests for multiplyBy10.
     */

    @Test
    public final void test_multiplyBy10_RecieverValue_0_int_value_0() {
        NaturalNumber testObj = this.constructorTest("0");
        NaturalNumber refObj = this.constructorRef("0");

        refObj.multiplyBy10(0);
        testObj.multiplyBy10(0);

        assertEquals(refObj, testObj);
    }

    @Test
    public final void test_multiplyBy10_RecieverValue_1_int_value_0() {
        NaturalNumber testObj = this.constructorTest("1");
        NaturalNumber refObj = this.constructorRef("1");

        refObj.multiplyBy10(0);
        testObj.multiplyBy10(0);

        assertEquals(refObj, testObj);
    }

    @Test
    public final void test_multiplyBy10_RecieverValue_0_int_value_1() {
        NaturalNumber testObj = this.constructorTest("0");
        NaturalNumber refObj = this.constructorRef("0");

        refObj.multiplyBy10(1);
        testObj.multiplyBy10(1);

        assertEquals(refObj, testObj);
    }

    @Test
    public final void test_multiplyBy10_RecieverValue_LargerThanMaxLong_int_value_1() {
        NaturalNumber testObj = this.constructorTest(Long.MAX_VALUE + "0");
        NaturalNumber refObj = this.constructorRef(Long.MAX_VALUE + "0");

        refObj.multiplyBy10(1);
        testObj.multiplyBy10(1);

        assertEquals(refObj, testObj);
    }

    @Test
    public final void test_multiplyBy10_RecieverValue_LargerThanMaxLong_int_value_9() {
        NaturalNumber testObj = this.constructorTest(Long.MAX_VALUE + "0");
        NaturalNumber refObj = this.constructorRef(Long.MAX_VALUE + "0");

        refObj.multiplyBy10(9);
        testObj.multiplyBy10(9);

        assertEquals(refObj, testObj);
    }

    /*
     * Several Tests for divideBy10.
     */

    @Test
    public final void test_divideBy10_RecieverValue0() {
        String s = "0";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        int testRem = nTest.divideBy10();
        int refRem = nRef.divideBy10();

        assertEquals(nRef, nTest);
        assertEquals(refRem, testRem);
    }

    @Test
    public final void test_divideBy10_RecieverValue1() {
        String s = "1";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        int testRem = nTest.divideBy10();
        int refRem = nRef.divideBy10();

        assertEquals(nRef, nTest);
        assertEquals(refRem, testRem);
    }

    @Test
    public final void test_divideBy10_RecieverValue100() {
        String s = "100";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        int testRem = nTest.divideBy10();
        int refRem = nRef.divideBy10();

        assertEquals(nRef, nTest);
        assertEquals(refRem, testRem);
    }

    @Test
    public final void test_divideBy10_RecieverValue_greater_than_long() {
        String s = Long.MAX_VALUE + "0";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        int testRem = nTest.divideBy10();
        int refRem = nRef.divideBy10();

        assertEquals(nRef, nTest);
        assertEquals(refRem, testRem);
    }

    /*
     * Several Tests for isZero.
     */

    @Test
    public final void test_isZero_NN_value_is_both_zero() {
        String s = "0";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        boolean boolTest = nTest.isZero();
        boolean boolRef = nRef.isZero();
        assertEquals(nRef, nTest);
        assertEquals(boolTest, boolRef);
    }

    /*
     * Several Tests for isZero.
     */

    @Test
    public final void test_isZero_NN_value_is_both_1() {
        String s = "1";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        boolean boolTest = nTest.isZero();
        boolean boolRef = nRef.isZero();
        assertEquals(nRef, nTest);
        assertEquals(boolTest, boolRef);
    }

    /*
     * Several Tests for isZero.
     */

    @Test
    public final void test_isZero_NN_value_is_both_greater_than_long() {
        String s = Long.MAX_VALUE + "0";
        NaturalNumber nTest = this.constructorTest(s);
        NaturalNumber nRef = this.constructorRef(s);

        boolean boolTest = nTest.isZero();
        boolean boolRef = nRef.isZero();
        assertEquals(nRef, nTest);
        assertEquals(boolTest, boolRef);
    }
}
