import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Sheen Brower
 */
public final class WordCounter {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private WordCounter() {

    }

    /**
     * Assigns Strings from an input file as keys (while ignoring separator
     * characters) to the number of times they occur as a value, storing these
     * pairs as a function.
     *
     * @requires input to be open
     * @param map
     *            the {@code Map} data type to store String keys and Integer
     *            values.
     * @param input
     *            the {@code SimpleReader} file that contains the strings
     * @param separators
     *            the {@code Set} data type containing characters to be ignored
     * @updates map
     * @ensures map has key and value pairings where key = a String found in
     *          input, and the int value stored is the number of times it
     *          appears in the input.
     */
    private static void wordCheck(Map<String, Integer> map, SimpleReader input,
            Set<Character> separators) {
        assert input.isOpen() : "Violation of: input is open";
        String word = "";
        char c = input.peek();
        while (!input.atEOS() && !separators.contains(c)) {
            c = input.peek();
            if (!separators.contains(c)) {
                c = input.read();
                word = word + c;
            }
        }
        if (map.hasKey(word)) {
            int val = map.value(word);
            map.replaceValue(word, val + 1);
        } else {
            map.add(word, 1);
        }
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";
        char c;
        if (str.length() > 0) {
            c = str.charAt(0);
            generateElements(str.substring(1), strSet);
            if (!strSet.contains(c)) {
                strSet.add(c);
            }
        }
    }

    /**
     * Fills a sequence when given a map where the sequence values are Strings
     * and the map consists of String keys and Integer values.
     *
     * @param map
     *            the {@code Map} with the wanted String keys
     * @param seq
     *            the {@code Sequence} that will be filled with map's keys
     * @updates seq
     * @ensures all of map's key String values are added to the front of map
     */
    private static void sequenceFromMap(Map<String, Integer> map,
            Sequence<String> seq) {
        for (Pair<String, Integer> pair : map) {
            String key = pair.key();
            seq.add(0, key);
        }
    }

    /**
     * Alphabetizes a Sequence.
     *
     * @requires alph is not empty
     * @param alph
     *            the {@code} Sequence that is not alphabetized yet
     * @updates alph
     * @ensures alph is in alphabetical order
     */
    private static void alphabetize(Sequence<String> alph) {
        assert alph.length() > 0 : "Violation of: alph.length() > 0";
        Sequence<String> temp = alph.newInstance();
        temp.transferFrom(alph);
        alph.add(0, temp.remove(0));
        while (temp.length() != 0) {
            String test = temp.remove(0);

            int i = 0;
            boolean inserted = false;
            while (i < alph.length() && !inserted) {
                if (compareStrings(test, alph.entry(i)).equals(test)) {
                    alph.add(i, test);
                    inserted = true;
                } else if (test.equals(alph.entry(i))) {
                    alph.add(i, test);
                    inserted = true;
                } else if (i == alph.length() - 1) {
                    alph.add(alph.length(), test);
                    inserted = true;
                }
                ++i;
            }
        }
    }

    /**
     * Compares two Strings and returns the larger String.
     *
     * @param a
     *            the {@code String} to compare
     * @param b
     *            the {@code String} to compare
     * @ensures return value is larger
     * @return the larger String
     */
    private static String compareStrings(String a, String b) {
        String aA = a.toUpperCase();
        String bB = b.toUpperCase();
        String smallest = "";
        char aC = '0';
        char bC = '0';
        if (aA.length() != 0) {
            aC = aA.charAt(0);
        }
        if (bB.length() != 0) {
            bC = bB.charAt(0);
        }
        if (aA.length() == 0 || aC < bC) {
            smallest = a;
        } else if (bB.length() == 0 || aC > bC) {
            smallest = b;
        } else if (bB.contains(aA)) {
            smallest = a;
        } else if (aA.contains(bB)) {
            smallest = b;
        } else {
            smallest = compareStrings(a.substring(1), b.substring(1));
            if (a.length() == b.length()) {
                if (a.contains(smallest)) {
                    smallest = a;
                } else if (b.contains(smallest)) {
                    smallest = b;
                }
            } else if (smallest.length() < 2) {

                if (a.length() < b.length()) {
                    smallest = a;
                } else {
                    smallest = b;
                }

            } else if (a.contains(smallest)) {
                smallest = a;
            } else {
                smallest = b;
            }
        }
        return smallest;

    }

    /**
     * Prints the HTML header with String input as title and header.
     *
     * @param out
     *            the {@code SimpleWriter} that is printing to a file
     * @param input
     *            the {@code String} to to print as HTML title and header
     * @ensures HTML header is printed with input as title and header
     */
    private static void printHeader(SimpleWriter out, String input) {
        out.print("<html>\r\n<head>\r\n<title>Input File Name:" + input
                + "</title>" + "\r\n<style>table, th, td\r\n{ border:"
                + "1px solid black;\r\n}\r\n</style>\r\n"
                + "</head>\r\n<body>\r\n<h2>Input File Name: " + input
                + "</h2>\r\n<hr />\r\n\r\n");
    }

    /**
     * prints the HTML body with a sequence of Strings, starting with index = 0,
     * in a column with a mapped Integer value pairing in an adjacent column.
     *
     * @param out
     *            the {@code SimpleWriter} to print to
     * @param map
     *            the {@code Map} where a String key is mapped to an Integer
     *            value
     * @param wordSeq
     *            the {@code Sequence} of Strings that will be printed with
     *            index = 0 being first
     *
     * @ensures HTML printed with wordSeq values starting at index = 0, and
     *          printing mapped values to each String in an adjacent column
     */
    private static void printBody(SimpleWriter out, Map<String, Integer> map,
            Sequence<String> wordSeq) {
        int i = 0;
        out.print(
                "<table>\r\n<tr>\r\n<th>Word</th><th>Count</th>\r\n</tr>\r\n");
        while (i < wordSeq.length()) {
            out.print("<tr>");
            String key = wordSeq.entry(i);
            int val = map.value(key);
            out.print("<td>" + key + "</td>" + "<td>" + val + "</td>\r\n");
            out.print("</tr>\r\n");
            i = i + 1;
        }
        out.print("</table>\r\n</body>\r\n</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        compareStrings("any", "and");
        final String separatorStr = " \t, ,\r,\n,-,.";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.println("Enter input file");
        String inputFileName = in.nextLine();
        out.println("Enter output file");
        String outputFileName = in.nextLine();
        SimpleReader input = new SimpleReader1L(inputFileName);
        SimpleWriter output = new SimpleWriter1L(outputFileName);

        Map<String, Integer> wordCount = new Map1L<>();
        while (!input.atEOS()) {
            char c = input.peek();
            if (!separatorSet.contains(c)) {
                wordCheck(wordCount, input, separatorSet);
            } else {
                input.read();
            }
        }

        Sequence<String> wordSeq = new Sequence1L<>();
        sequenceFromMap(wordCount, wordSeq);
        alphabetize(wordSeq);

        printHeader(output, inputFileName);
        printBody(output, wordCount, wordSeq);
        in.close();
        out.close();
        input.close();
        output.close();
    }

}
