package utils;

public class TestUtils {

	/**
	 * The horizontal Separator for messages.
	 */
	private static final String SEPARATOR_HORIZONTAL = "=========================================";
	public static final int TESTELEMENT_AMOUNT = 100000;

	public static void printMessageWithHorizontalSeperator(final String message) {
		System.out.println(SEPARATOR_HORIZONTAL);
		System.out.println(message);
		System.out.println(SEPARATOR_HORIZONTAL);
	}

	public static void printStartingTestMessage(final String testname) {
		System.out.println(SEPARATOR_HORIZONTAL);
		System.out.println("Starting test with : " + testname);
		System.out.println(SEPARATOR_HORIZONTAL);
	}

	public static void printStopWatchResult(final String testname, final String stopWatchResult) {
		System.out.println(SEPARATOR_HORIZONTAL);
		System.out.println(
				"Time for copying " + TESTELEMENT_AMOUNT + " elements with " + testname + ": " + stopWatchResult);
		System.out.println(SEPARATOR_HORIZONTAL);
	}

}
