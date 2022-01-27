import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Sheen Brower
 * @author John Choi
 *
 */
public final class Program1Parse1 extends Program1 {

	/*
	 * Private members --------------------------------------------------------
	 */

	/**
	 * Parses a single BL instruction from {@code tokens} returning the instruction
	 * name as the value of the function and the body of the instruction in
	 * {@code body}.
	 *
	 * @param tokens the input tokens
	 * @param body   the instruction body
	 * @return the instruction name
	 * @replaces body
	 * @updates tokens
	 * @requires
	 * 
	 *           <pre>
	 * [<"INSTRUCTION"> is a prefix of tokens]  and
	 *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
	 *           </pre>
	 * 
	 * @ensures
	 * 
	 *          <pre>
	 * if [an instruction string is a proper prefix of #tokens]  and
	 *    [the beginning name of this instruction equals its ending name]  and
	 *    [the name of this instruction does not equal the name of a primitive
	 *     instruction in the BL language] then
	 *  parseInstruction = [name of instruction at start of #tokens]  and
	 *  body = [Statement corresponding to statement string of body of
	 *          instruction at start of #tokens]  and
	 *  #tokens = [instruction string at start of #tokens] * tokens
	 * else
	 *  [report an appropriate error message to the console and terminate client]
	 *          </pre>
	 */
	private static String parseInstruction(Queue<String> tokens, Statement body) {
		assert tokens != null : "Violation of: tokens is not null";
		assert body != null : "Violation of: body is not null";
		assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
			+ "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

		String checkInstruction = tokens.dequeue();
		Reporter.assertElseFatalError(checkInstruction.equals("INSTRUCTION"),
			"Error: Keywords \"BEGIN\" or \"INSTRUCTION\" expected, found: \""
				+ checkInstruction + "\"");
		String instructionName = tokens.dequeue();
		Reporter.assertElseFatalError(Tokenizer.isIdentifier(instructionName),
			"Error: IDENTIFIER expected, found: \"" + instructionName + "\"");

		String isCheck = tokens.dequeue();
		Reporter.assertElseFatalError(isCheck.equals("IS"),
			"Error: Keyword \"IS\" expected, found: \"" + isCheck + "\"");

		body.parseBlock(tokens);

		String endCheck = tokens.dequeue();
		Reporter.assertElseFatalError(endCheck.equals("END"),
			"Error: Keyword \"END\" expected, found: \"" + endCheck + "\"");

		String nameFound = tokens.dequeue();
		Reporter.assertElseFatalError(nameFound.equals(instructionName),
			"Error: IDENTIFIER \"" + instructionName + "\" expected, found: \""
				+ nameFound + "\"");

		return instructionName;
	}

	/*
	 * Constructors -----------------------------------------------------------
	 */

	/**
	 * No-argument constructor.
	 */
	public Program1Parse1() {
		super();
	}

	/*
	 * Public methods ---------------------------------------------------------
	 */

	@Override
	public void parse(SimpleReader in) {
		assert in != null : "Violation of: in is not null";
		assert in.isOpen() : "Violation of: in.is_open";
		Queue<String> tokens = Tokenizer.tokens(in);
		this.parse(tokens);
	}

	@Override
	public void parse(Queue<String> tokens) {
		assert tokens != null : "Violation of: tokens is not null";
		assert tokens.length() > 0 : ""
			+ "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

		Map<String, Statement> tempContext = this.newContext();
		Statement bodyBlock = this.newBody();

		if (tokens.front().equals("PROGRAM")) {
			tokens.dequeue();
			String nameCheck = tokens.dequeue();
			Reporter.assertElseFatalError(Tokenizer.isIdentifier(nameCheck),
				"Error: IDENTIFIER expected, found: \"" + nameCheck + "\"");
			this.setName(nameCheck);

			String isCheck = tokens.dequeue();
			Reporter.assertElseFatalError(isCheck.equals("IS"),
				"Error: Keyword \"IS\" expected, found: \"" + isCheck + "\"");

			while (tokens.front().equals("INSTRUCTION")) {
				Statement tempBlock = this.newBody();
				String instructionName = parseInstruction(tokens, tempBlock);
				tempContext.add(instructionName, tempBlock);
			}

			if (tokens.front().equals("BEGIN")) {
				String beginTest = tokens.dequeue();
				Reporter.assertElseFatalError(beginTest.equals("BEGIN"),
					"Error: Keyword \"BEGIN\" expected, found: \"" + beginTest + "\"");
				bodyBlock.parseBlock(tokens);
			}
			String endCheck = tokens.dequeue();
			Reporter.assertElseFatalError(endCheck.equals("END"),
				"Error: Keyword \"END\" expected, found: \"" + endCheck + "\"");

			String nameFound = tokens.dequeue();
			Reporter.assertElseFatalError(nameFound.equals(nameCheck),
				"Error: IDENTIFIER \"" + nameFound + "\" expected, found: \"" + nameFound
					+ "\"");

			this.swapBody(bodyBlock);
			this.swapContext(tempContext);
		} else {
			Reporter.fatalErrorToConsole(
				"Error: Keyword \"PROGRAM\" expected, found: \"" + tokens.front() + "\"");
		}

		Reporter.assertElseFatalError(tokens.length() == 1,
			"Error: found \"extra\" beyond end of program source");
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
		out.print("Enter valid BL program file name: ");
		// String fileName = in.nextLine();FIXME
		String fileName = "test/program2.bl";
		/*
		 * Parse input file
		 */
		out.println("*** Parsing input file ***");
		Program p = new Program1Parse1();
		SimpleReader file = new SimpleReader1L(fileName);
		Queue<String> tokens = Tokenizer.tokens(file);
		file.close();
		p.parse(tokens);
		/*
		 * Pretty print the program
		 */
		out.println("*** Pretty print of parsed program ***");
		p.prettyPrint(out);

		in.close();
		out.close();
	}

}
