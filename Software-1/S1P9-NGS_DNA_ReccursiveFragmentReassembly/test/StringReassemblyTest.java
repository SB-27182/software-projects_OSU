import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void combination_0_0() {
       String str1 = "bobby";
       String str2 = "hill";
       int overlap = 0;
       String result = StringReassembly.combination(str1, str2, overlap);
       assertEquals("bobbyhill", result);
    }

    @Test
    public void combination_1_12() {
       String str1 = "sounds like alot of";
       String str2 = "like alot of hooplah over a crabby patty";
       int overlap = 12;
       String result = StringReassembly.combination(str1, str2, overlap);
       assertEquals("sounds like alot of hooplah over a crabby patty", result);
    }
    @Test
    public void combination_2_18() {
       String str1 = "wingardium leviosa";
       String str2 = "wingardium leviosaah";
       int overlap = 18;
       String result = StringReassembly.combination(str1, str2, overlap);
       assertEquals("wingardium leviosaah", result);
    }
    @Test
    public void combination_3_11() {
       String str1 = "FUHS ROH DAH";
       String str2 = "UHS ROH DAH";
       int overlap = 11;
       String result = StringReassembly.combination(str1, str2, overlap);
       assertEquals("FUHS ROH DAH", result);
    }
    //addToSetAvoidingSubstrings

    @Test
    public void addToSetAvoidingSubstrings_0_0() {
        Set<String> sTest = new Set1L<String>();
        sTest.add("dood");
        sTest.add("pood");
        sTest.add("rood");
        StringReassembly.addToSetAvoidingSubstrings(sTest, "doodle");

        assertEquals("{pood,rood,doodle}", sTest.toString());
    }
    @Test
    public void addToSetAvoidingSubstrings_1_0() {
        Set<String> sTest = new Set1L<String>();
        sTest.add("dood");
        sTest.add("pood");
        sTest.add("rood");
        StringReassembly.addToSetAvoidingSubstrings(sTest, "doodpood");
        assertEquals("{rood,doodpood}", sTest.toString());
    }
    @Test
    public void addToSetAvoidingSubstrings_2_0() {
        Set<String> sTest = new Set1L<String>();
        sTest.add("dood");
        sTest.add("pood");
        sTest.add("rood");
        StringReassembly.addToSetAvoidingSubstrings(sTest, "doodlepoodleroodle");
        assertEquals("{doodlepoodleroodle}", sTest.toString());
    }
    //linesFromInput

    @Test
    public void linesFromInput_0_0() {
        SimpleReader inFile = new SimpleReader1L("jUnitTest1.txt");
        Set<String> fragments = StringReassembly.linesFromInput(inFile);
        assertEquals("{ be elected; whereby the legislative powers,~     incapable of }",
        fragments.toString());
    }
    @Test
    public void linesFromInput_1_0() {
        SimpleReader inFile = new SimpleReader1L("jUnitTest2.txt");
        Set<String> fragments = StringReassembly.linesFromInput(inFile);
        assertEquals("{Declaration of Independence~~                   [A}",
        fragments.toString());
    }
    //printWithLineSepperators

    @Test
    public void printWithLineSeparators_1_0() {
        SimpleWriter out = new SimpleWriter1L();
        String text = "~bot";
        StringReassembly.printWithLineSeparators(text, out);
        System.out.println("\n----------");
        String printThis = "\nbot";
        out.print(printThis);
    }
}
