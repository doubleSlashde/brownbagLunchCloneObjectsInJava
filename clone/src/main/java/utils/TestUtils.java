package utils;

import java.util.ArrayList;
import java.util.List;

import exampleObjects.Car;
import exampleObjects.Engine;
import exampleObjects.Manufacturer;
import exampleObjects.SA;

public class TestUtils {

	private static final String MESSAGE_SEPARATOR = "=========================================";
	public static final int TESTELEMENT_AMOUNT = 10000;

	/**
	 * Initializes the given array with random ExampleObjects.
	 * 
	 * @param carArray
	 *            the objectArray
	 */
	public static void initializeExampleObjectArray(final Car[] carArray) {
		for (int i = 0; i < carArray.length; i++) {
			int pseudoRandomNumber = (int) (Math.random() * 100);
			System.out.println("Created pseudo random number: " + pseudoRandomNumber);

			// Initializing Car
			Car car = new Car();
			car.setId(pseudoRandomNumber);

			// Initializing SaList
			List<SA> saList = new ArrayList<SA>();
			for (int j = 0; j < 100; j++) {
				saList.add(new SA(pseudoRandomNumber));
			}
			// car.setSaList(saList);

			// Initializing Engine
			Engine engine = new Engine();
			engine.setSerialNumber(pseudoRandomNumber);

			// Initializing Manufacturer
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setManufacturerNumber(pseudoRandomNumber);

			// Set Manufacturer in Engine
			engine.setManufacturer(manufacturer);

			// Set Engine in Car
			car.setEngine(engine);
			System.out.println("Created element nr. " + (i + 1) + "\n" + car);
			carArray[i] = car;
		}
	}

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
