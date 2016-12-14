package utils;

public class TestUtils {

	private static final String MESSAGE_SEPARATOR = "=========================================";
	public static final int TESTELEMENT_AMOUNT = 100000;

	public static void printMessageWithHorizontalSeperator(final String message) {
		System.out.println(MESSAGE_SEPARATOR);
		System.out.println(message);
		System.out.println(MESSAGE_SEPARATOR);
	}

	public static void printStartingTestMessage(final String testname) {
		System.out.println(MESSAGE_SEPARATOR);
		System.out.println("Starting test with : " + testname);
		System.out.println(MESSAGE_SEPARATOR);
	}

	public static void printStopWatchResult(final String testname, final String stopWatchResult) {
		System.out.println(MESSAGE_SEPARATOR);
		System.out.println(
				"Time for copying " + TESTELEMENT_AMOUNT + " elements with " + testname + ": " + stopWatchResult);
		System.out.println(MESSAGE_SEPARATOR);
	}

}
