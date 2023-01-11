/**
 * The NoPreviousElementException class is used when the user attempts to
 * perform an operation that fails because the cursor is on the first element.
 * 
 * @author Sean Erfan
 */
public class NoPrevElementException extends Exception {
	/**
	 * Sets message of exception to state that the cursor is the last element of
	 * the list
	 */
	public NoPrevElementException() {
		super("No previous car, cannot move cursor backward.");
	}
}
