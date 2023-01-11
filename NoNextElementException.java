/**
 * The NoNextElement class is used when the user attempts to perform an
 * operation that fails because the cursor is on the last element.
 * 
 * @author Sean Erfan
 */
public class NoNextElementException extends Exception {
	/**
	 * Sets message of exception to state that the cursor is the last element of
	 * the list
	 */
	public NoNextElementException() {
		super("No next car, cannot move cursor forward.");
	}
}
