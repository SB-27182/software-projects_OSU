import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * @return Returns the square root.
     * @param x
     */
    private static double sqrt(double x, double sum) {

        double r = x;
        double s = Math.abs(Math.pow(r, 2) - x);

        if (x == 0) {
            r = 0;
        }
        else {

            while ((s / x) > sum) {
                r = (r + (x / r)) / 2;
                s = Math.abs(Math.pow(r, 2) - x);
            }
        }
        return r;
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
        char y = 'y';
        double x;
        double e;


        out.print("What value for e?");
        e = in.nextDouble();

        while (y == 'y') {

            out.println("Enter X");
            x = in.nextDouble();
            if (x < 0) {
                out.println("Time to quit.");
                break;
            }

            x = sqrt(x, e);
            out.print(x + "\n");

        }




        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
