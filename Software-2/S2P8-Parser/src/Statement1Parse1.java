import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Sheen Brower
 * @author John Choi
 *
 */
public final class Statement1Parse1 extends Statement1 {

	/*
	 * Private members --------------------------------------------------------
	 */

	/**
	 * Converts {@code c} into the corresponding {@code Condition}.
	 *
	 * @param c the condition to convert
	 * @return the {@code Condition} corresponding to {@code c}
	 * @requires [c is a condition string]
	 * @ensures parseCondition = [Condition corresponding to c]
	 */
	private static Condition parseCondition(String c) {
		assert c != null : "Violation of: c is not null";
		assert Tokenizer.isCondition(c) : "Violation of: c is a condition string";
		return Condition.valueOf(c.replace('-', '_').toUpperCase());
	}

	/**
	 * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
	 *
	 * @param tokens the input tokens
	 * @param s      the parsed statement
	 * @replaces s
	 * @updates tokens
	 * @requires
	 * 
	 *           <pre>
	 * [<"IF"> is a prefix of tokens]  and
	 *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
	 *           </pre>
	 * 
	 * @ensures
	 * 
	 *          <pre>
	 * if [an if string is a proper prefix of #tokens] then
	 *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
	 *  #tokens = [if string at start of #tokens] * tokens
	 * else
	 *  [reports an appropriate error message to the console and terminates client]
	 *          </pre>
	 */
	private static void parseIf(Queue<String> tokens, Statement s) {
		assert tokens != null : "Violation of: tokens is not null";
		assert s != null : "Violation of: s is not null";
		assert tokens.length() > 0 && tokens.front().equals("IF") : ""
			+ "Violation of: <\"IF\"> is proper prefix of tokens";

		tokens.dequeue();

		String stringCondition = tokens.dequeue();
		Reporter.assertElseFatalError(Tokenizer.isCondition(stringCondition),
			"Error: CONDTION expected, found: \"" + stringCondition + "\"");

		Condition ifCond = parseCondition(stringCondition);

		String testForTHEN = tokens.dequeue();
		Reporter.assertElseFatalError(testForTHEN.equals("THEN"),
			"Error: Keyword \"THEN\" expected, found: \"" + testForTHEN + "\"");

		Statement tempBlock = s.newInstance();
		while (!tokens.front().equals("END") && !tokens.front().equals("ELSE")) {
			tempBlock.parseBlock(tokens);
		}

		if (tokens.front().equals("ELSE")) {
			tokens.dequeue();
			Statement elseBlock = s.newInstance();

			while (!tokens.front().equals("END")) {
				elseBlock.parseBlock(tokens);
			}
			s.assembleIfElse(ifCond, tempBlock, elseBlock);

		} else {
			s.assembleIf(ifCond, tempBlock);
		}

		tokens.dequeue();

		String testForIF = tokens.dequeue();
		Reporter.assertElseFatalError(testForIF.equals("IF"),
			"Error: Keyword \"IF\" expected, found: \"" + testForIF + "\"");

	}

	/**
	 * Parses a WHILE statement from {@code tokens} into {@code s}.
	 *
	 * @param tokens the input tokens
	 * @param s      the parsed statement
	 * @replaces s
	 * @updates tokens
	 * @requires
	 * 
	 *           <pre>
	 * [<"WHILE"> is a prefix of tokens]  and
	 *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
	 *           </pre>
	 * 
	 * @ensures
	 * 
	 *          <pre>
	 * if [a while string is a proper prefix of #tokens] then
	 *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
	 *  #tokens = [while string at start of #tokens] * tokens
	 * else
	 *  [reports an appropriate error message to the console and terminates client]
	 *          </pre>
	 */
	private static void parseWhile(Queue<String> tokens, Statement s) {
		assert tokens != null : "Violation of: tokens is not null";
		assert s != null : "Violation of: s is not null";
		assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
			+ "Violation of: <\"WHILE\"> is proper prefix of tokens";

		tokens.dequeue();
		String stringCondition = tokens.dequeue();
		Reporter.assertElseFatalError(Tokenizer.isCondition(stringCondition),
			"Error: CONDTION expected, found: \"" + stringCondition + "\"");
		Condition whileCond = parseCondition(stringCondition);

		String testForDO = tokens.dequeue();
		Reporter.assertElseFatalError(testForDO.equals("DO"),
			"Error: Keyword \"DO\" expected, found: \"" + testForDO + "\"");
		Statement tempBlock = s.newInstance();

		while (!tokens.front().equals("END")) {
			tempBlock.parseBlock(tokens);

		}
		s.assembleWhile(whileCond, tempBlock);
		tokens.dequeue();
		String testForWHILE = tokens.dequeue();
		Reporter.assertElseFatalError(testForWHILE.equals("WHILE"),
			"Error: Keyword \"WHILE\" expected, found: \"" + testForWHILE + "\"");

	}

	/**
	 * Parses a CALL statement from {@code tokens} into {@code s}.
	 *
	 * @param tokens the input tokens
	 * @param s      the parsed statement
	 * @replaces s
	 * @updates tokens
	 * @requires [identifier string is a proper prefix of tokens]
	 * @ensures
	 *
	 *          <pre>
	 * s =
	 *   [CALL Statement corresponding to identifier string at start of #tokens]  and
	 *  #tokens = [identifier string at start of #tokens] * tokens
	 *          </pre>
	 */
	private static void parseCall(Queue<String> tokens, Statement s) {
		assert tokens != null : "Violation of: tokens is not null";
		assert s != null : "Violation of: s is not null";
		assert tokens.length() > 0 && Tokenizer.isIdentifier(tokens.front()) : ""
			+ "Violation of: identifier string is proper prefix of tokens";

		s.assembleCall(tokens.dequeue());

	}

	/*
	 * Constructors -----------------------------------------------------------
	 */

	/**
	 * No-argument constructor.
	 */
	public Statement1Parse1() {
		super();
	}

	/*
	 * Public methods ---------------------------------------------------------
	 */

	@Override
	public void parse(Queue<String> tokens) {
		assert tokens != null : "Violation of: tokens is not null";
		assert tokens.length() > 0 : ""
			+ "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

		boolean foundELSE = false;

		if (tokens.front().equals("ELSE")) {
			foundELSE = true;
		} else if (!foundELSE) {
			if (tokens.front().equals("IF")) {
				parseIf(tokens, this);
			} else if (tokens.front().equals("WHILE")) {
				parseWhile(tokens, this);

			} else {
				parseCall(tokens, this);
			}
		}
	}

	@Override
	public void parseBlock(Queue<String> tokens) {
		assert tokens != null : "Violation of: tokens is not null";
		assert tokens.length() > 0 : ""
			+ "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

		while (!tokens.front().equals("END") && !tokens.front().equals("ELSE")
			&& !tokens.front().equals("### END OF INPUT ###")) {
			Statement tempBlock = this.newInstance();

			tempBlock.parse(tokens);
			this.addToBlock(this.lengthOfBlock(), tempBlock);
		}

	}

	/*
	 * Main test method -------------------------------------------------------
	 */

	/**
	 * Main method.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SimpleReader in = new SimpleReader1L();
		SimpleWriter out = new SimpleWriter1L();
		/*
		 * Get input file name
		 */
		out.print("Enter valid BL statement(s) file name: ");
		String fileName = in.nextLine();
		/*
		 * Parse input file
		 */
		out.println("*** Parsing input file ***");
		Statement s = new Statement1Parse1();
		SimpleReader file = new SimpleReader1L(fileName);
		Queue<String> tokens = Tokenizer.tokens(file);
		file.close();

		s.parseBlock(tokens);

		/*
		 * Pretty print the statement(s)
		 */
		out.println("*** Pretty print of parsed statement(s) ***");
		s.prettyPrint(out, 0);

		in.close();
		out.close();
	}

}
