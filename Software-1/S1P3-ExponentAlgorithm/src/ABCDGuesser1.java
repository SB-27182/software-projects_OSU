import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
 * Repeatedly asks the user for a positive real number until the user enters
 * one. Returns the positive real number.
 * 
 * @param in
 *            the input stream
 * @param out
 *            the output stream
 * @return a positive real number entered by the user
 */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String dString = "";
        double d = -1;
        while (d == -1) {
            out.print("It must be a positive double value: ");
            dString = in.nextLine();
            if (FormatChecker.canParseDouble(dString)) {
                if (Double.parseDouble(dString) > 0.0) {
                    d = Double.parseDouble(dString);
                }
            }
        }
        return d;
    }
  
/**
 * Repeatedly asks the user for a positive real number not equal to 1.0
 * until the user enters one. Returns the positive real number.
 * 
 * @param in
 *            the input stream
 * @param out
 *            the output stream
 * @return a positive real number not equal to 1.0 entered by the user
 */
    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
        String dString = "";
        double d = -1;
        while (d == -1) {
            out.print("It must be a positive double value > 1.0: ");
            dString = in.nextLine();
            if (FormatChecker.canParseDouble(dString)) {
                if (Double.parseDouble(dString) > 1.0) {
                    d = Double.parseDouble(dString);
                }
            }
        }
        return d;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
       final double[] EXPOS = {-5,-4,-3,-2,-1,-0.5, -0.3333333333, -0.25, 0, 0.25, 0.33333333333, 0.5, 1, 2, 3, 4, 5};
        double[] userDubs = new double[4];
        double w = 0, x = 0, y = 0, z = 0;
        String[] abcd = {"A. ", "B. ", "C. ", "D. "};

        int i = 0;
        double mew;
        while (i < userDubs.length) {
            out.print("Enter value for " + abcd[i]);
            userDubs[i] = getPositiveDoubleNotOne(in,out);
            i = i + 1;
        }
        out.print("Enter user constant Mew: ");
        mew = getPositiveDouble(in,out);

       //double[] userDubs = {14,102329,1936,13};
        mew = 238900;
        double r = 0;
        double error = 100;
        int b,c,d,a = 0;
        while (a < EXPOS.length) {// LOOPING MOST INSIDE FIRST
            b = 0;
            while (b < EXPOS.length) {
                c = 0;
                while (c < EXPOS.length) {
                    d = 0;
                    while (d < EXPOS.length) {
                        r = Math.pow(userDubs[0], EXPOS[a]) * Math.pow(userDubs[1], EXPOS[b]) * Math.pow(userDubs[2],EXPOS[c]) * Math.pow(userDubs[3], EXPOS[d]);
                        if ((Math.abs((mew - r)/mew * 100)) < error) {
                            w = EXPOS[a];
                            x = EXPOS[b];
                            y = EXPOS[c];
                            z = EXPOS[d];
                            error = (Math.abs((mew - r)/mew * 100));
                        }
                        d = d + 1;
                    }//d
                    c = c + 1;
                }//c
                b = b + 1;
            }//b
            a = a + 1;
        }//a


       out.print("With relative error " + error + ". W,X,Y,Z are " + w+", " + x+", "  + y+", " + z);
        in.close();
        out.close();
    }

}
