package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cloneExample.CloneUtils;
import complex.ExampleObject;
import complex.ExampleObject2;
import primitives.ExampleObject3;

public class DeepCloneWithStopWatchTest {

	/**
	 * Length of the testArray. Number of elements to be cloned
	 */
	private static final int ARRAY_LEN = 10000;
	private ExampleObject[] exampleObjectArray = new ExampleObject[ARRAY_LEN];

	private StopWatch stopWatch;
	private String serializationTime;
	private String reflectionTime;

	/**
	 * Initializes the given array with random ExampleObjects.
	 * 
	 * @param objectArray
	 *            the objectArray
	 */
	private void initializeExampleObjectArray(final ExampleObject[] objectArray) {
		for (int i = 0; i < objectArray.length; i++) {
			int pseudoRandomNumber = (int) (Math.random() * 100);
			System.out.println("Created pseudo random number: " + pseudoRandomNumber);

			// Initializing ExampleObject
			ExampleObject exampleObject = new ExampleObject();
			exampleObject.setNumber(pseudoRandomNumber);

			// Initializing ExampleObject2
			ExampleObject2 exampleObject2 = new ExampleObject2();
			exampleObject2.setNumber(pseudoRandomNumber);

			// Initializing ExampleObject3
			ExampleObject3 exampleObject3 = new ExampleObject3();
			exampleObject3.setNumber(pseudoRandomNumber);

			// Set exampleObject3 into exampleObject2
			exampleObject2.setExampleObject3(exampleObject3);

			// Set exampleObject2 into exampleObject
			exampleObject.setExampleObject2(exampleObject2);
			System.out.println("Created element nr. " + (i + 1) + "\n" + exampleObject);
			objectArray[i] = exampleObject;
		}
	}

	@Before
	public void initTimer() {
		stopWatch = new StopWatch();
	}

	@Test
	public void test_Reflection() throws Exception {

		// Setup: Create 1000 objects
		ExampleObject[] clonedArray = new ExampleObject[ARRAY_LEN];
		System.out.println("=========================================");
		System.out.println("Starting test with Reflection");
		System.out.println("=========================================");
		initializeExampleObjectArray(exampleObjectArray);
		// Clone-call: Start Stopwatch
		stopWatch.start();
		int j = 0;
		for (ExampleObject exampleObject : exampleObjectArray) {
			clonedArray[j++] = CloneUtils.copyWithSerialization(exampleObject);
		}

		// End of clone
		stopWatch.stop();
		reflectionTime = stopWatch.toString();
		stopWatch.reset();

		// Check test result
		int i = 0;
		for (ExampleObject exampleObject : clonedArray) {
			Assert.assertEquals("", exampleObjectArray[i++], exampleObject);
		}
		System.out.println("=========================================");
		System.out.println("Time for copying " + ARRAY_LEN + " exampleObjects with Reflection: " + reflectionTime);
		System.out.println("=========================================");
		exampleObjectArray = null;
	}

	@Test
	public void test_Serialization() throws Exception {

		// Setup: Create 1000 objects
		ExampleObject[] clonedArray = new ExampleObject[ARRAY_LEN];
		System.out.println("=========================================");
		System.out.println("Starting test with Serialization");
		System.out.println("=========================================");
		initializeExampleObjectArray(exampleObjectArray);
		// Clone-call: Start Stopwatch
		stopWatch.start();
		int j = 0;
		for (ExampleObject exampleObject : exampleObjectArray) {
			clonedArray[j++] = CloneUtils.copyWithReflection(exampleObject);
		}

		// End of clone
		stopWatch.stop();
		serializationTime = stopWatch.toString();
		stopWatch.reset();

		// Check test result
		int i = 0;
		for (ExampleObject exampleObject : clonedArray) {
			Assert.assertEquals("", exampleObjectArray[i++], exampleObject);
		}
		System.out.println("=========================================");
		System.out
				.println("Time for copying " + ARRAY_LEN + " exampleObjects with Serialization: " + serializationTime);
		System.out.println("=========================================");
	}

}
