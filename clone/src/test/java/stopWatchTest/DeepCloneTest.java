package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cloneMethods.Reflection;
import cloneMethods.Serialize;
import exampleObjects.Car;
import exampleObjects.Engine;
import exampleObjects.Manufacturer;
import utils.TestUtils;

public class DeepCloneTest {

	private Car[] testCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
	private StopWatch stopWatch;
	private String testname;

	private void checkTestResult(Car[] clonedCarArray) {

		// Check clonedArray
		int i = 0;
		for (Car exampleObject : clonedCarArray) {
			Assert.assertEquals("Not the expected Car", testCarArray[i++], exampleObject);
		}

		// Change first Object from original
		Engine firstEngineFromCarArray = testCarArray[0].getEngine();
		int newSerialNumber = (int) (Math.random() * 100);
		while (newSerialNumber == firstEngineFromCarArray.getSerialNumber()) {
			newSerialNumber = (int) (Math.random() * 100);
		}
		firstEngineFromCarArray.setSerialNumber(newSerialNumber);

		// Check if the changed Object is not in the clonedArray
		Assert.assertNotEquals("SerialNumber of Engine not change.", testCarArray[0].getEngine().getSerialNumber(),
				clonedCarArray[0].getEngine().getSerialNumber());
	}

	@Before
	public void init() {
		stopWatch = new StopWatch();
		initializeExampleObjectArray(testCarArray);
	}

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

	@After
	public void resetTimer() {
		stopWatch.reset();
	}

	@Test
	public void test_CopyConstructor() {

		// Setup: Create 1000 objects
		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		String copyConstructorTime;
		testname = "Copy-Constructor";
		TestUtils.printStartingTestMessage(testname);

		// Starting cloning -> Start StopWatch
		TestUtils.printMessageWithHorizontalSeperator("Start cloning");
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = new Car(car);
		}
		// End of clone
		stopWatch.stop();
		TestUtils.printMessageWithHorizontalSeperator("End of cloning");
		copyConstructorTime = stopWatch.toString();

		checkTestResult(clonedCarArray);
		TestUtils.printStopWatchResult(testname, copyConstructorTime);
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
		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		String reflectionTime;
		testname = "Reflection";
		TestUtils.printStartingTestMessage(testname);

		// Starting cloning -> Start StopWatch
		TestUtils.printMessageWithHorizontalSeperator("Start cloning");
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = Reflection.deepCopyWithReflection(car);
		}
		// End of clone
		stopWatch.stop();
		TestUtils.printMessageWithHorizontalSeperator("End of cloning");
		reflectionTime = stopWatch.toString();

		checkTestResult(clonedCarArray);
		TestUtils.printStopWatchResult(testname, reflectionTime);
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
		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		String serializationTime;
		testname = "Serialization";
		TestUtils.printStartingTestMessage(testname);

		// Starting cloning -> Start StopWatch
		TestUtils.printMessageWithHorizontalSeperator("Start cloning");
		stopWatch.start();
		int j = 0;
		for (Car exampleObject : testCarArray) {
			clonedCarArray[j++] = Serialize.deepCopyWithSerialization(exampleObject);
		}

		// End of clone -> Stop StopWatch and reset it
		stopWatch.stop();
		TestUtils.printMessageWithHorizontalSeperator("End of cloning");
		serializationTime = stopWatch.toString();

		checkTestResult(clonedCarArray);

		TestUtils.printStopWatchResult(testname, serializationTime);
	}

}
