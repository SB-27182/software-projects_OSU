import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {
        if (exp.label().equals("expression")) {
            components.utilities.Reporter.fatalErrorToConsole("root is <expression>");
        }
        assert exp != null : "Violation of: exp is not null";
        String operator = "";
        NaturalNumber operand0 = new NaturalNumber2(0);
        NaturalNumber operand1 = new NaturalNumber2(0);
        NaturalNumber returnInt = new NaturalNumber2(0);
        NaturalNumber zero = new NaturalNumber2(0);
        int xmlInt = 0;

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
                operand0.add(operand1);
                returnInt.transferFrom(operand0);
                operand1.clear();
            }
            if (operator.equals("-") || operator.equals("minus")) {
                if (operand0.compareTo(operand1) < 0) {
                    components.utilities.Reporter.fatalErrorToConsole("NaturalNumber"
                            + " subtract requires this >= n.");
                }
                operand0.subtract(operand1);
                returnInt.transferFrom(operand0);
                operand1.clear();
            }
            if (operator.equals("*") || operator.equals("times")) {
                operand0.multiply(operand1);
                returnInt.transferFrom(operand0);
                operand1.clear();
            }
            if (operator.equals("/") || operator.equals("divide")) {
                if (operand1.compareTo(zero) <= 0) {
                    components.utilities.Reporter.fatalErrorToConsole("NaturalNumber"
                            + " divide requires n > 0.");
                }
                operand0.divide(operand1);
                returnInt.transferFrom(operand0);
                operand1.clear();
            }
        } else {

            if (exp.numberOfChildren() == 0) {

                if (exp.hasAttribute("value")) {
                    xmlInt = Integer.parseInt(exp.attributeValue("value"));
                    returnInt.setFromInt(xmlInt);
                } else if (!exp.isTag()) {
                    xmlInt = Integer.parseInt(exp.attributeValue("value"));
                    returnInt.setFromInt(xmlInt);
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