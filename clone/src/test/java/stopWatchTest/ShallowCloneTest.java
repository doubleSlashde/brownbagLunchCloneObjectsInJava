package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cloneMethods.CloneMethods;
import exampleObjects.Car;
import exampleObjects.Engine;
import exampleObjects.Manufacturer;
import utils.TestUtils;

public class ShallowCloneTest {

	private Car[] testCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
	private StopWatch stopWatch;
	private String testname;

	/**
	 * Initializes the given array with random ExampleObjects.
	 * 
	 * @param carArray
	 *            the objectArray
	 */
	private void initializeExampleObjectArray(final Car[] carArray) {
		for (int i = 0; i < carArray.length; i++) {
			int pseudoRandomNumber = (int) (Math.random() * 100);
			System.out.println("Pseudorandom number nr." + (i + 1) + ": " + pseudoRandomNumber);

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
		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		String beanUtilsTime;
		testname = "Bean Utils";
		TestUtils.printStartingTestMessage(testname);
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
		Engine firstEngineFromCarArray = testCarArray[0].getEngine();
		int newSerialNumber = (int) (Math.random() * 100);
		while (newSerialNumber == firstEngineFromCarArray.getSerialNumber()) {
			newSerialNumber = (int) (Math.random() * 100);
		}
		firstEngineFromCarArray.setSerialNumber(newSerialNumber);

		// Check if the changed Object has affected the clonedArray
		Assert.assertEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());
		TestUtils.printStopWatchResult(testname, beanUtilsTime);
		testCarArray = null;
	}

	/**
	 * Test for Copy with the Object.clone() method and the Cloneable-Interface.
	 * 
	 * @throws Exception
	 *             the Exception
	 */
	@Test
	public void test_Cloneable() throws Exception {
		// Setup
		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		String cloneableTime;
		testname = "Cloneable Interface and clone()";
		TestUtils.printStartingTestMessage(testname);
		initializeExampleObjectArray(testCarArray);

		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = car.cloneCar();
		}

		// End of clone
		stopWatch.stop();
		cloneableTime = stopWatch.toString();
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
		Engine firstEngineFromCarArray = testCarArray[0].getEngine();
		int newSerialNumber = (int) (Math.random() * 100);
		while (newSerialNumber == firstEngineFromCarArray.getSerialNumber()) {
			newSerialNumber = (int) (Math.random() * 100);
		}
		firstEngineFromCarArray.setSerialNumber(newSerialNumber);

		// Check if the changed Object has affected the clonedArray
		Assert.assertEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());
		TestUtils.printStopWatchResult(testname, cloneableTime);
		testCarArray = null;
	}

}