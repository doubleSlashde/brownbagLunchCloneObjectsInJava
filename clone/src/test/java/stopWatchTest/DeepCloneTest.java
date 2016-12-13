package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cloneMethods.CloneMethods;
import commonUtils.Utils;
import exampleObjectsWithReferences.Car;
import exampleObjectsWithReferences.Engine;
import exampleObjectsWithoutReferences.Manufacturer;

public class DeepCloneTest {

	/**
	 * The startMessage for the tests.
	 */
	private static final String STARTING_TEST_MESSAGE = "Starting test with ";

	/**
	 * Length of the testArray. Number of elements to be cloned
	 */
	private static final int TESTELEMENT_AMOUNT = 100000;

	/**
	 * The Array holding the cars for the test.
	 */
	private Car[] testCarArray = new Car[TESTELEMENT_AMOUNT];

	/**
	 * The StopWatch
	 */
	private StopWatch stopWatch;

	/**
	 * Initializes the given array with random ExampleObjects.
	 * 
	 * @param carArray
	 *            the objectArray
	 */
	private void initializeExampleObjectArray(final Car[] carArray) {
		for (int i = 0; i < carArray.length; i++) {
			int pseudoRandomNumber = (int) (Math.random() * 100);
			System.out.println("Created pseudo random number: " + pseudoRandomNumber);

			// Initializing Car
			Car car = new Car();
			car.setId(pseudoRandomNumber);

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

	@Before
	public void init() {
		stopWatch = new StopWatch();
		initializeExampleObjectArray(testCarArray);
	}

	@After
	public void resetTimer() {
		stopWatch.reset();
	}

	@Test
	public void test_CopyConstructor() {

		// Setup: Create 1000 objects
		Car[] clonedCarArray = new Car[TESTELEMENT_AMOUNT];
		String reflectionTime;
		Utils.printMessageWithHorizontalSeperator(STARTING_TEST_MESSAGE + "Copy Constructor");

		// Starting cloning -> Start StopWatch
		Utils.printMessageWithHorizontalSeperator("Start cloning");
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = new Car(car);
		}
		// End of clone
		stopWatch.stop();
		Utils.printMessageWithHorizontalSeperator("End of cloning");
		reflectionTime = stopWatch.toString();

		// Check test result
		int i = 0;
		for (Car exampleObject : clonedCarArray) {
			Assert.assertEquals("Not the expected Car", testCarArray[i++], exampleObject);
		}

		// Check precondition
		Assert.assertEquals("Precondition not met.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());

		// Change first Object from original
		Engine firstEngineFromCarArray = testCarArray[0].getEngine();
		int newSerialNumber = (int) (Math.random() * 100);
		while (newSerialNumber == firstEngineFromCarArray.getSerialNumber()) {
			newSerialNumber = (int) (Math.random() * 100);
		}
		firstEngineFromCarArray.setSerialNumber(newSerialNumber);
		testCarArray[0].setEngine(firstEngineFromCarArray);

		// Check if the changed Object is not in the clonedArray
		Assert.assertNotEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());
		Utils.printMessageWithHorizontalSeperator(
				"Time for copying " + TESTELEMENT_AMOUNT + " exampleObjects with Copy Constructor: " + reflectionTime);
		testCarArray = null;

	}

	/**
	 * Test for Copy with Reflection. Creates a deep Copy of an array of cars
	 * and make some checks.
	 * 
	 * @throws Exception
	 *             the Exception
	 */
	@Test
	public void test_Reflection() throws Exception {

		// Setup: Create 1000 objects
		Car[] clonedCarArray = new Car[TESTELEMENT_AMOUNT];
		String reflectionTime;
		Utils.printMessageWithHorizontalSeperator(STARTING_TEST_MESSAGE + "Reflection");

		// Starting cloning -> Start StopWatch
		Utils.printMessageWithHorizontalSeperator("Start cloning");
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = CloneMethods.deepCopyWithReflection(car);
		}
		// End of clone
		stopWatch.stop();
		Utils.printMessageWithHorizontalSeperator("End of cloning");
		reflectionTime = stopWatch.toString();

		// Check test result
		int i = 0;
		for (Car exampleObject : clonedCarArray) {
			Assert.assertEquals("Not the expected Car", testCarArray[i++], exampleObject);
		}

		// Check precondition
		Assert.assertEquals("Precondition not met.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());

		// Change first Object from original
		Engine firstEngineFromCarArray = testCarArray[0].getEngine();
		int newSerialNumber = (int) (Math.random() * 100);
		while (newSerialNumber == firstEngineFromCarArray.getSerialNumber()) {
			newSerialNumber = (int) (Math.random() * 100);
		}
		firstEngineFromCarArray.setSerialNumber(newSerialNumber);
		testCarArray[0].setEngine(firstEngineFromCarArray);

		// Check if the changed Object is not in the clonedArray
		Assert.assertNotEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());
		Utils.printMessageWithHorizontalSeperator(
				"Time for copying " + TESTELEMENT_AMOUNT + " exampleObjects with Reflection: " + reflectionTime);
		testCarArray = null;
	}

	/**
	 * Test for Copy with Serialization. Creates a deep copy of an array of cars
	 * and make some checks.
	 * 
	 * @throws Exception
	 *             the Exception
	 */
	@Test
	public void test_Serialization() throws Exception {

		// Setup
		Car[] clonedCarArray = new Car[TESTELEMENT_AMOUNT];
		String serializationTime;
		Utils.printMessageWithHorizontalSeperator(STARTING_TEST_MESSAGE + "Serialization");

		// Starting cloning -> Start StopWatch
		Utils.printMessageWithHorizontalSeperator("Start cloning");
		stopWatch.start();
		int j = 0;
		for (Car exampleObject : testCarArray) {
			clonedCarArray[j++] = CloneMethods.deepCopyWithSerialization(exampleObject);
		}

		// End of clone -> Stop StopWatch and reset it
		stopWatch.stop();
		Utils.printMessageWithHorizontalSeperator("End of cloning");
		serializationTime = stopWatch.toString();

		// Check test result
		int i = 0;
		for (Car car : clonedCarArray) {
			Assert.assertEquals("Not the expected Car", testCarArray[i++], car);
		}

		// Check precondition
		Assert.assertEquals("Precondition not met.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());

		// Change 1 Object from original
		Engine firstEngineFromCarArray = testCarArray[0].getEngine();
		int newSerialNumber = (int) (Math.random() * 100);
		while (newSerialNumber == firstEngineFromCarArray.getSerialNumber()) {
			newSerialNumber = (int) (Math.random() * 100);
		}
		firstEngineFromCarArray.setSerialNumber(newSerialNumber);
		testCarArray[0].setEngine(firstEngineFromCarArray);

		// Check if the changed Object is not in the clonedArray
		Assert.assertNotEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());

		Utils.printMessageWithHorizontalSeperator(
				"Time for copying " + TESTELEMENT_AMOUNT + " exampleObjects with Serialization: " + serializationTime);
	}

}
