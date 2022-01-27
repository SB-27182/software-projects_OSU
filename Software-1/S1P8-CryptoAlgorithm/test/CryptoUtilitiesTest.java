import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Sheen Brower
 * 
 */
public class CryptoUtilitiesTest {

    /**
     * Tests of reduceToGCD.
     */
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("0", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_1_0() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("1", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(21);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("3", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_15_0() {
        NaturalNumber n = new NaturalNumber2(15);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("15", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_1_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(1);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("1", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_79_300() {
        NaturalNumber n = new NaturalNumber2(79);
        NaturalNumber m = new NaturalNumber2(300);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("1", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_999_9() {
        NaturalNumber n = new NaturalNumber2(999);
        NaturalNumber m = new NaturalNumber2(9);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("9", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_12344_50() {
        NaturalNumber n = new NaturalNumber2(12344);
        NaturalNumber m = new NaturalNumber2(50);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("2", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_6000_12000() {
        NaturalNumber n = new NaturalNumber2(6000);
        NaturalNumber m = new NaturalNumber2(12000);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("6000", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_100000_1000000() {
        NaturalNumber n = new NaturalNumber2(100000);
        NaturalNumber m = new NaturalNumber2(1000000);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("100000", n.toString());
        assertEquals("0", m.toString());
    }

     /**
     * Tests of isEven.
     */
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("0", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("1", n.toString());
        assertTrue(!result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("2", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_40() {
        NaturalNumber n = new NaturalNumber2(40);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("40", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_60001() {
        NaturalNumber n = new NaturalNumber2(60001);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("60001", n.toString());
        assertTrue(!result);
    }

    @Test
    public void testIsEven_60000() {
        NaturalNumber n = new NaturalNumber2(60000);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("60000", n.toString());
        assertTrue(result);
    }
    @Test
    public void testIsEven_5() {
        NaturalNumber n = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("5", n.toString());
        assertTrue(!result);
    }

    /**
     * Tests of powerMod.
     */
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("2", m.toString());
    }

    @Test
    public void testPowerMod_1_1_2() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("1", p.toString());
        assertEquals("2", m.toString());
    }
    @Test
    public void testPowerMod_305_72_19() {
        NaturalNumber n = new NaturalNumber2(305);
        NaturalNumber p = new NaturalNumber2(72);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("72", p.toString());
        assertEquals("19", m.toString());
    }
    @Test
    public void testPowerMod_1_500_2() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(500);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("500", p.toString());
        assertEquals("2", m.toString());
    }
    @Test
    public void testPowerMod_500_1_19() {
        NaturalNumber n = new NaturalNumber2(500);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("6", n.toString());
        assertEquals("1", p.toString());
        assertEquals("19", m.toString());
    }
    @Test
    public void testPowerMod_7_15_2() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber p = new NaturalNumber2(15);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("15", p.toString());
        assertEquals("2", m.toString());
    }
    @Test
    public void testPowerMod_7_0_19() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("19", m.toString());
    }
    @Test
    public void testPowerMod_0_5_19() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(5);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("0", n.toString());
        assertEquals("5", p.toString());
        assertEquals("19", m.toString());
    }
    @Test
    public void testPowerMod_117_18_19() {
        NaturalNumber n = new NaturalNumber2(117);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("18", p.toString());
        assertEquals("19", m.toString());
    }
     /**
     * Tests of isWtinessToComposite.
     */
    @Test
    public void testisWitnessToComposite_3_5() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(5);
        boolean composite = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals("3", w.toString());
        assertEquals("5", n.toString());
        assertTrue(!composite);
    }
    @Test
    public void testisWitnessToComposite_700_703() {
        NaturalNumber w = new NaturalNumber2(700);
        NaturalNumber n = new NaturalNumber2(702);
        boolean composite = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals("700", w.toString());
        assertEquals("702", n.toString());
        assertTrue(composite);
    }
    /**
     * Tests of isPrime2.
     */
    @Test
    public void testisPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals("2", n.toString());
        assertTrue(prime);
    }
    @Test
    public void testisPrime2_12547() {
        NaturalNumber n = new NaturalNumber2(12547);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals("12547", n.toString());
        assertTrue(prime);
    }
    @Test
    public void testisPrime2_12549() {
        NaturalNumber n = new NaturalNumber2(12549);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals("12549", n.toString());
        assertTrue(!prime);
    }
    @Test
    public void testisPrime2_100() {
        NaturalNumber n = new NaturalNumber2(100);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals("100", n.toString());
        assertTrue(!prime);
    }
    @Test
    public void testisPrime2_9() {
        NaturalNumber n = new NaturalNumber2(9);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals("9", n.toString());
        assertTrue(!prime);
    }
    @Test
    public void testisPrime2_24977() {
        NaturalNumber n = new NaturalNumber2(24977);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals("24977", n.toString());
        assertTrue(prime);
    }
     /**
     * Tests of generateNextLikelyPrime.
     */
    @Test
    public void testgenerateNextLikelyPrime_12549() {
        NaturalNumber n = new NaturalNumber2(12549);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("12553", n.toString());
    }
    @Test
    public void testgenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("2", n.toString());
    }
    @Test
    public void testgenerateNextLikelyPrime_18311() {
        NaturalNumber n = new NaturalNumber2(18311);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("18311", n.toString());
    }
    @Test
    public void testgenerateNextLikelyPrime_53113() {
        NaturalNumber n = new NaturalNumber2(53113);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("53113", n.toString());
    }
    @Test
    public void testgenerateNextLikelyPrime_53112() {
        NaturalNumber n = new NaturalNumber2(53112);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("53113", n.toString());
    }
    @Test
    public void testgenerateNextLikelyPrime_53114() {
        NaturalNumber n = new NaturalNumber2(53114);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("53117", n.toString());
    }
    @Test
    public void testgenerateNextLikelyPrime_53201() {
        NaturalNumber n = new NaturalNumber2(53201);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("53201", n.toString());
    }
    @Test
    public void testgenerateNextLikelyPrime_53202() {
        NaturalNumber n = new NaturalNumber2(53202);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("53231", n.toString());
    }

}