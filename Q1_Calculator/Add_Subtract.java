package BitnineSWE;


import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;             //importing ScriptEngine for arithmatic evaluation
import javax.script.ScriptException;
import java.util.Scanner;                     //import scanner to take inputs


public class Add_Subtract {

	public static void main(String[] args) throws ScriptException, NumTooLargeException, NumberFormatException {   //main method that throws exceptions
		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Input: ");               //take in user expression
				String calc = input.nextLine();
				System.out.println(calculate(calc));

			} catch (NumTooLargeException e) {    //catch num too large error  or invalid arithmetic sequence
				System.out.println("Please enter numbers below 100,000,000!");
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid arithmetic sequence!");
			}
		}

	}

	public static String calculate(String calc) throws ScriptException, NumTooLargeException {
		if (calc.equals("0")) {    //if user enters 0, exit program 
			System.out.println("Have a nice day!");
			System.exit(0);
		}
		ScriptEngineManager mgr = new ScriptEngineManager();    //use JavaScript engine to evaluate math expressions
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		calc = calc.replaceAll("\\s+", "");   //delete all spaces within expression

		String temp = calc;           //create string array and loop through to check max number
		temp = temp.replaceAll("[()-]", "");
		temp = temp.replaceAll(" ", "");
		temp = temp.replaceAll("[-+*/=]", ",");
		String[] splitted = temp.split(",");
		for (int i = 0; i < splitted.length; i++) {
			if (Integer.valueOf(splitted[i]) > 100000000) {
				throw new NumTooLargeException();
			}
		}

		return String.valueOf(engine.eval(calc));   //returns the String value of the answer
	}

}


