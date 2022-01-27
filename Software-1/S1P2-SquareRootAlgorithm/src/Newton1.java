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
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * @return Returns the sqrt up to estimation x.
     * @param x
     */
    private static double sqrt(double x) {
        double sum = .00000001;
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



        while (y == 'y') {

            out.print("What square root do you want?\n");
            x = in.nextDouble();

            x = sqrt(x);
            out.print(x + "\n");

            out.print("Thank-you, may I have another? y/n\n");
            y = in.nextLine().charAt(0);
        }




        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
