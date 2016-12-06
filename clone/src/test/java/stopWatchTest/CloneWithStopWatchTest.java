package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Before;
import org.junit.Test;

import cloneExample.CloneUtils;
import complex.ExampleObject;
import complex.ExampleObject2;
import complex.ExampleObject3;

public class CloneWithStopWatchTest {

	/**
	 * Length of the testArray. Number of elements to be cloned
	 */
	private static final int ARRAY_LEN = 10_000;

	private StopWatch stopWatch;

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
			ExampleObject exampleObject = new ExampleObject();
			exampleObject.setNumber(pseudoRandomNumber);

			ExampleObject2 exampleObject2 = new ExampleObject2();
			exampleObject2.setNumber(pseudoRandomNumber);

			ExampleObject3 exampleObject3 = new ExampleObject3();
			exampleObject3.setNumber(pseudoRandomNumber);
			exampleObject2.setExampleObject3(exampleObject3);
			exampleObject.setOtherObject(exampleObject2);
			System.out.println("Created element nr. " + (i + 1) + "\n" + exampleObject);
			objectArray[i] = exampleObject;
		}
	}

	@Before
	public void initTimer() {
		stopWatch = new StopWatch();
	}

	@Test
	public void test_Serialization() throws Exception {

		// Setup: Create 1000 objects
		ExampleObject[] objectArray = new ExampleObject[ARRAY_LEN];
		ExampleObject[] clonedArray = new ExampleObject[objectArray.length];
		initializeExampleObjectArray(objectArray);
		// Clone-call: Start Stopwatch
		stopWatch.start();
		int j = 0;
		for (ExampleObject exampleObject : objectArray) {
			clonedArray[j++] = CloneUtils.copyWithSerialization(exampleObject);
		}

		// End of clone
		stopWatch.stop();

		System.out.println("=========================================");
		System.out.println("Copy time: " + stopWatch);
		System.out.println("=========================================");
	}

}
