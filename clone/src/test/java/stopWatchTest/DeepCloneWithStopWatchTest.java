package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cloneExample.CloneUtils;
import complex.Car;
import complex.Engine;
import primitives.Manufacturer;

public class DeepCloneWithStopWatchTest {

	/**
	 * Length of the testArray. Number of elements to be cloned
	 */
	private static final int TESTELEMENT_AMOUNT = 10000;
	private Car[] exampleObjectArray = new Car[TESTELEMENT_AMOUNT];

	private StopWatch stopWatch;

	/**
	 * Initializes the given array with random ExampleObjects.
	 * 
	 * @param objectArray
	 *            the objectArray
	 */
	private void initializeExampleObjectArray(final Car[] objectArray) {
		for (int i = 0; i < objectArray.length; i++) {
			int pseudoRandomNumber = (int) (Math.random() * 100);
			System.out.println("Created pseudo random number: " + pseudoRandomNumber);

			// Initializing ExampleObject
			Car car = new Car();
			car.setId(pseudoRandomNumber);

			// Initializing ExampleObject2
			Engine engine = new Engine();
			engine.setSerialNumber(pseudoRandomNumber);

			// Initializing ExampleObject3
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setManufacturerNumber(pseudoRandomNumber);

			// Set exampleObject3 into exampleObject2
			engine.setManufacturer(manufacturer);

			// Set exampleObject2 into exampleObject
			car.setEngine(engine);
			System.out.println("Created element nr. " + (i + 1) + "\n" + car);
			objectArray[i] = car;
		}
	}

	@Before
	public void initTimer() {
		stopWatch = new StopWatch();
	}

	@Test
	public void test_CopyConstructor() {

	}

	@Test
	public void test_Reflection() throws Exception {

		// Setup: Create 1000 objects
		Car[] clonedArray = new Car[TESTELEMENT_AMOUNT];
		String reflectionTime;
		System.out.println("=========================================");
		System.out.println("Starting test with Reflection");
		System.out.println("=========================================");
		initializeExampleObjectArray(exampleObjectArray);
		// Clone-call: Start Stopwatch
		stopWatch.start();
		int j = 0;
		for (Car exampleObject : exampleObjectArray) {
			clonedArray[j++] = CloneUtils.copyWithReflection(exampleObject);
		}

		// End of clone
		stopWatch.stop();
		reflectionTime = stopWatch.toString();
		stopWatch.reset();

		// Check test result
		int i = 0;
		for (Car exampleObject : clonedArray) {
			Assert.assertEquals("", exampleObjectArray[i++], exampleObject);
		}
		System.out.println("=========================================");
		System.out.println(
				"Time for copying " + TESTELEMENT_AMOUNT + " exampleObjects with Reflection: " + reflectionTime);
		System.out.println("=========================================");
		exampleObjectArray = null;
	}

	@Test
	public void test_Serialization() throws Exception {

		// Setup
		Car[] clonedArray = new Car[TESTELEMENT_AMOUNT];
		String serializationTime;
		System.out.println("=========================================");
		System.out.println("Starting test with Serialization");
		System.out.println("=========================================");
		initializeExampleObjectArray(exampleObjectArray);

		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car exampleObject : exampleObjectArray) {
			clonedArray[j++] = CloneUtils.copyWithSerialization(exampleObject);
		}

		// End of clone -> Stop StopWatch and reset it
		stopWatch.stop();
		serializationTime = stopWatch.toString();
		stopWatch.reset();

		// Check test result
		int i = 0;
		for (Car exampleObject : clonedArray) {
			Assert.assertEquals("", exampleObjectArray[i++], exampleObject);
		}

		// Change 1 Object from original
		Engine objectToChange = exampleObjectArray[0].getEngine();
		int newNumberForObject = (int) (Math.random() * 100);
		Assert.assertNotEquals("Number not changed.", objectToChange.getSerialNumber(), newNumberForObject);
		objectToChange.setSerialNumber(newNumberForObject);
		exampleObjectArray[0].setEngine(objectToChange);

		// Check if the changed Object is not in the clonedArray
		Assert.assertNotEquals(exampleObjectArray[0].getEngine().getSerialNumber(),
				clonedArray[0].getEngine().getSerialNumber());

		System.out.println("=========================================");
		System.out.println(
				"Time for copying " + TESTELEMENT_AMOUNT + " exampleObjects with Serialization: " + serializationTime);
		System.out.println("=========================================");
	}

}
