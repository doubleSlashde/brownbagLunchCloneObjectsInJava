package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cloneMethods.CloneMethods;
import commonUtils.Utils;
import exampleObjectsWithReferences.Car;
import exampleObjectsWithReferences.Engine;
import exampleObjectsWithoutReferences.Manufacturer;

public class ShallowCloneWithStopWatchTest {

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

	@Test
	public void test_BeanUtils() throws Exception {

		// Setup
		Car[] clonedCarArray = new Car[TESTELEMENT_AMOUNT];
		String beanUtilsTime;
		Utils.printMessageWithHorizontalSeperator(STARTING_TEST_MESSAGE + "BeanUtils");
		initializeExampleObjectArray(testCarArray);

		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = CloneMethods.shallowCopyWithBeanUtils(car);
		}

		// End of clone
		stopWatch.stop();
		beanUtilsTime = stopWatch.toString();
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

		// Check if the changed Object has affected the clonedArray
		Assert.assertEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());
		Utils.printMessageWithHorizontalSeperator(
				"Time for copying " + TESTELEMENT_AMOUNT + " exampleObjects with BeanUtils: " + beanUtilsTime);
		testCarArray = null;
	}

	/**
	 * Test for Copy with the Object.clone() method and the Cloneable-Interface.
	 * 
	 * @throws Exception
	 *             the Exception
	 */
	@Ignore("Needs to be implemented.")
	@Test
	public void test_Cloneable() throws Exception {

	}

}