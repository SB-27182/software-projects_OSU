import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        if (exp.label().equals("expression")) {
            components.utilities.Reporter.fatalErrorToConsole("root is <expression>");
        }
        assert exp != null : "Violation of: exp is not null";
        String operator = "";
        int operand0 = 0;
        int operand1 = 0;
        int returnInt = 0;

        if (exp.label().equals("plus") || exp.label().equals("minus")
        || exp.label().equals("divide") || exp.label().equals("times")
        || exp.label().equals("+") || exp.label().equals("-")
        || exp.label().equals("/") || exp.label().equals("*")) {

            operator = exp.label();
        }

        if (exp.hasAttribute("operator")) {
            if (exp.attributeValue("operator").equals("plus")
            || exp.attributeValue("operator").equals("minus")
                || exp.attributeValue("operator").equals("divide")
                || exp.attributeValue("operator").equals("times")
                || exp.attributeValue("operator").equals("+")
                || exp.attributeValue("operator").equals("-")
                || exp.attributeValue("operator").equals("/")
                || exp.attributeValue("operator").equals("*")) {

                operator = exp.attributeValue("operator");
            }
        }

        for (int i = 0; i < exp.numberOfChildren(); i++) {
            XMLTree child = exp.child(i);

            if (!child.isTag()) {
                if (child.label().equals("plus") || child.label().equals("minus")
                 || child.label().equals("divide") || child.label().equals("times")
                 || child.label().equals("+") || child.label().equals("-")
                 || child.label().equals("/") || child.label().equals("*")) {
                    operator = child.label();
                }
            }
        }


        if (!operator.equals("")) {

                operand0 = evaluate(exp.child(0));
                operand1 = evaluate(exp.child(1));


            if (operator.equals("+") || operator.equals("plus")) {
                returnInt = operand0 + operand1;
            }
            if (operator.equals("-") || operator.equals("minus")) {
                returnInt = operand0 - operand1;
            }
            if (operator.equals("*") || operator.equals("times")) {
                returnInt = operand0 * operand1;
            }
            if (operator.equals("/") || operator.equals("divide")) {
                if (operand1 == 0) {
                    components.utilities.Reporter.fatalErrorToConsole("Divided by 0.");
                }
                returnInt = operand0 / operand1;
            }
        } else {

            if (exp.numberOfChildren() == 0) {

                if (exp.hasAttribute("value")) {
                    returnInt = Integer.parseInt(exp.attributeValue("value"));
                } else if (!exp.isTag()) {
                    returnInt = Integer.parseInt(exp.child(0).label());
                }
            }
        }

        return returnInt;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}