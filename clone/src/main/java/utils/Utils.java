package utils;

public class Utils {

	/**
	 * The horizontal Separator for messages.
	 */
	private static final String SEPARATOR_HORIZONTAL = "=========================================";

	/**
	 * Prints the given message with horizontal separator before and after the
	 * message. <br>
	 * <br>
	 * <b>Result looks like:<br>
	 * </b> ========================================= <br>
	 * message<br>
	 * =========================================
	 * 
	 * @param message
	 *            the message
	 */
	public static void printMessageWithHorizontalSeperator(final String message) {
		System.out.println(SEPARATOR_HORIZONTAL);
		System.out.println(message);
		System.out.println(SEPARATOR_HORIZONTAL);
	}

}
