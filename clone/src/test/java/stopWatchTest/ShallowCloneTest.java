package stopWatchTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cloneMethods.BeanUtil;
import exampleObjects.Car;
import exampleObjects.Engine;
import utils.TestUtils;

public class ShallowCloneTest {

	private Car[] testCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
	private StopWatch stopWatch = new StopWatch();
	private String testname;

	private void checkTestResult(Car[] clonedCarArray) {
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
	}

	@Before
	public void initTestArray() {
		TestUtils.initializeExampleObjectArray(testCarArray);
	}

	@After
	public void resetTimer() {
		stopWatch.reset();
	}

	@Test
	public void test_BeanUtils() throws Exception {

		// Setup
		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		testname = "Bean Utils";
		TestUtils.printStartingTestMessage(testname);

		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = BeanUtil.shallowCopyWithBeanUtils(car);
		}

		// End of clone
		stopWatch.stop();

		checkTestResult(clonedCarArray);
		TestUtils.printStopWatchResult(testname, stopWatch.toString());
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
		testname = "Cloneable Interface and clone()";
		TestUtils.printStartingTestMessage(testname);
		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = car.cloneCar();
		}

		// End of clone
		stopWatch.stop();

		checkTestResult(clonedCarArray);
		TestUtils.printStopWatchResult(testname, stopWatch.toString());
		testCarArray = null;
	}

}