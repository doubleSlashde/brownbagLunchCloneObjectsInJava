package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cloneExample.CloneUtils;
import complex.Car;
import complex.Engine;
import primitives.Manufacturer;
import utils.Utils;

public class DeepCloneWithStopWatchTest {

	/**
	 * The startMessage for the tests.
	 */
	private static final String STARTING_TEST_MESSAGE = "Starting test with ";

	/**
	 * Length of the testArray. Number of elements to be cloned
	 */
	private static final int TESTELEMENT_AMOUNT = 10000;

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
	public void initTimer() {
		stopWatch = new StopWatch();
	}

	@Ignore("Needs to be implemented.")
	@Test
	public void test_CopyConstructor() {

	}

	/**
	 * Test for Copy with Reflection. Creates a deep Copy of an array of cars
	 * and make some checks.
	 * 
	 * @throws Exception
	 *             the Exception
	 */
	// TODO: Check why the condition after changing one object does not met.
	@Test
	public void test_Reflection() throws Exception {

		// Setup: Create 1000 objects
		Car[] clonedCarArray = new Car[TESTELEMENT_AMOUNT];
		String reflectionTime;
		Utils.printMessageWithHorizontalSeperator(STARTING_TEST_MESSAGE + "Reflection");
		initializeExampleObjectArray(testCarArray);

		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = CloneUtils.deepCopyWithReflection(car);
		}

		// End of clone
		stopWatch.stop();
		reflectionTime = stopWatch.toString();
		stopWatch.reset();

		// Check test result
		int i = 0;
		for (Car exampleObject : clonedCarArray) {
			Assert.assertEquals("Not the expected Car", testCarArray[i++], exampleObject);
		}

		// Check precondition
		Assert.assertEquals("Precondition not met.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());

		// Change 1 Object from original
		Engine engineToChange = testCarArray[0].getEngine();
		int newNumberForObject = (int) (Math.random() * 100);
		Assert.assertNotEquals("Number not changed.", engineToChange.getSerialNumber(), newNumberForObject);
		engineToChange.setSerialNumber(newNumberForObject);
		testCarArray[0].setEngine(engineToChange);

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
		initializeExampleObjectArray(testCarArray);

		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car exampleObject : testCarArray) {
			clonedCarArray[j++] = CloneUtils.deepCopyWithSerialization(exampleObject);
		}

		// End of clone -> Stop StopWatch and reset it
		stopWatch.stop();
		serializationTime = stopWatch.toString();
		stopWatch.reset();

		// Check test result
		int i = 0;
		for (Car car : clonedCarArray) {
			Assert.assertEquals("Not the expected Car", testCarArray[i++], car);
		}

		// Check precondition
		Assert.assertEquals("Precondition not met.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());

		// Change 1 Object from original
		Engine engineToChange = testCarArray[0].getEngine();
		int newNumberForObject = (int) (Math.random() * 100);
		Assert.assertNotEquals("Number not changed.", engineToChange.getSerialNumber(), newNumberForObject);
		engineToChange.setSerialNumber(newNumberForObject);
		testCarArray[0].setEngine(engineToChange);

		// Check if the changed Object is not in the clonedArray
		Assert.assertNotEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());

		Utils.printMessageWithHorizontalSeperator(
				"Time for copying " + TESTELEMENT_AMOUNT + " exampleObjects with Serialization: " + serializationTime);
	}

}
