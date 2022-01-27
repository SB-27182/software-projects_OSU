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
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
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
 * Prints the values for w x y z with additional text to describe them.
 * @param w    double value
 * @param x    double value
 * @param y    double value
 * @param z    double value
 * @param error double value
 * @param out the output stream
 */
    private static void additionalMethodPrint(double w, double x, double y, double z, double error, SimpleWriter out) {
        out.print("With relative error " + error + ". W,X,Y,Z are " + w+", " + x+", "  + y+", " + z);
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
        for (a = 0; a < EXPOS.length; a = a + 1){// LOOPING MOST INSIDE FIRST

           for (b = 0; b < EXPOS.length; b = b + 1) {

                for (c = 0; c < EXPOS.length; c = c + 1) {

                    for (d = 0; d < EXPOS.length; d = d + 1) {
                        r = Math.pow(userDubs[0], EXPOS[a]) * Math.pow(userDubs[1], EXPOS[b]) * Math.pow(userDubs[2],EXPOS[c]) * Math.pow(userDubs[3], EXPOS[d]);
                        if ((Math.abs((mew - r)/mew * 100)) < error) {
                            w = EXPOS[a];
                            x = EXPOS[b];
                            y = EXPOS[c];
                            z = EXPOS[d];
                            error = (Math.abs((mew - r)/mew * 100));
                        }

                    }//d

                }//c

            }//b

        }//a


       additionalMethodPrint(w,x,y,z,error,out);



        in.close();
        out.close();
    }

}
