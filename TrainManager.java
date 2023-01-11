import java.util.Scanner;

/**
 * The TrainManager receives and interprets input from the user and sends it to
 * the TrainLinkedList class
 * 
 * @author Sean Erfan
 */
public class TrainManager {
	static Scanner s;
	static TrainLinkedList train;

	/**
	 * Initializes Scanner and both Planners, and runs the entire program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		s = new Scanner(System.in);
		train = new TrainLinkedList();

		showMenu();

		String input = s.nextLine().toUpperCase();
		while (!input.equals("Q")) {
			System.out.println();
			useInput(input);
			showMenu();
			input = s.nextLine().toUpperCase();
		}
		System.out.println("Program terminating successfully...");
	}

	/**
	 * Prints menu listing every input option to the user.
	 */
	public static void showMenu() {
		System.out.print("(F) Cursor Forward\n" + "(B) Cursor Backward\n"
		        + "(I) Insert Car After Cursor\n" + "(R) Remove Car At Cursor\n"
		        + "(L) Set Product Load\n" + "(S) Search For Product\n"
		        + "(T) Display Train\n" + "(M) Display Manifest\n"
		        + "(D) Remove Dangerous Cars\n" + "(Q) Quit\n"
		        + "\nEnter a selection: ");
	}

	/**
	 * Calls methods depending on the option that the user selected.
	 * 
	 * @param input The input given by the user
	 */
	public static void useInput(String input) {
		switch (input) {
		case ("F"):
			try {
				train.cursorForward();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;

		case ("B"):
			try {
				train.cursorBackward();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;

		case ("I"):
			double length;
			double weight;
			while (true) {
				length = promptNextDouble("Enter car length in meters: ");
				if (length <= 0) {
					System.out.println("Length must greater than 0");
					continue;
				}
				break;
			}
			while (true) {
				weight = promptNextDouble("Enter car weight in tons: ");
				if (weight <= 0) {
					System.out.println("Weight must be greater than 0");
					continue;
				}
				break;
			}
			System.out.println();
			train.insertAfterCursor(new TrainCar(length, weight));
			break;

		case ("R"):
			try {
				TrainCar c = train.removeCursor();

				System.out.println("Car successfully unlinked. "
				        + "The following load has been removed from the train:");
				c.getLoad().printSingleLoad();
			}
			catch (NullCursorException e) {
				System.out.println(e.getMessage());
			}
			break;

		case ("L"):
			ProductLoad l = new ProductLoad();
			String inp;
			while (true) {
				try {
					inp = promptNextString("Enter produce name: ");
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}
			l.setName(inp);

			while (l.getWeight() == 0) {
				try {
					l.setWeight(
					        promptNextDouble("Enter produce weight in tons: "));
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			while (l.getValue() == 0) {
				try {
					l.setValue(promptNextDouble(
					        "Enter produce value in dollars: "));
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			String danger = promptNextString(
			        "Enter is product dangerous? " + "(y/n): ").toLowerCase();
			while (!danger.equals("y") && !danger.equals("n")) {
				System.out.println("Must enter 'y' or 'n'");
				danger = promptNextString("Enter is product dangerous? (y/n): ")
				        .toLowerCase();
			}
			System.out.println();
			l.setDangerous(danger.equals("y"));

			try {
				train.LoadCursor(l);
			}
			catch (NullCursorException e) {
				System.out.println(e.getMessage());
			}
			break;

		case ("S"):
			train.findProduct(promptNextString("Enter product name: "));
			break;

		case ("T"):
			System.out.println(train);
			break;

		case ("M"):
			train.printManifest();
			break;

		case ("D"):
			train.removeDangerousCars();
			break;

		default:
			System.out.println("Not a menu option.");
			break;
		}

		System.out.println();

	}

	/**
	 * Prints out a message and returns the String input
	 * 
	 * @param msg The message to print.
	 * @return The user input
	 */
	public static String promptNextString(String msg) {
		System.out.print(msg);
		return s.nextLine();
	}

	/**
	 * Prints out a message and returns a double input. If the input is not a
	 * double, it will try again.
	 * 
	 * @param msg The message to print
	 * @return The user input
	 */
	public static double promptNextDouble(String msg) {
		double d;
		while (true) {
			try {
				System.out.print(msg);
				d = Double.parseDouble(s.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Must enter a number");
				continue;
			}
			break;
		}
		return d;
	}
}