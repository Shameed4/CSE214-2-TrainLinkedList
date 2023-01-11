/**
 * The NullCursorException class is used when the user attempts to perform an
 * operation with the cursor when it is null
 * 
 * @author Sean Erfan
 */
public class NullCursorException extends Exception {
	/**
	 * Sets message of exception to state that the list is empty and that the
	 * operation could not be performed.
	 * 
	 * @param msg A string to be added to this Exception's message.
	 * 
	 */
	public NullCursorException(String msg) {
		super("List is empty. " + msg);
	}
}