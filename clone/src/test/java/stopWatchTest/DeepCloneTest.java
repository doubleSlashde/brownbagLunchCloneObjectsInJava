package stopWatchTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cloneMethods.Reflection;
import cloneMethods.SerializationUtil;
import cloneMethods.Serialize;
import exampleObjects.Car;
import exampleObjects.Engine;
import exampleObjects.SA;
import utils.TestUtils;

public class DeepCloneTest {

	private Car[] testCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
	private StopWatch stopWatch;
	private String testname;

	private void checkTestResult(Car[] clonedCarArray) {

		int i = 0;
		for (Car clonedCar : clonedCarArray) {
			Assert.assertEquals("Car in original array and cloned array is not the same.", //
					testCarArray[i++], //
					clonedCar);
		}

		// Change engine from first original car
		Car firstOriginalCar = testCarArray[0];
		Car firstClonedCar = clonedCarArray[0];
		Engine firstEngineFromCarArray = firstOriginalCar.getEngine();
		int newSerialNumber = (int) (Math.random() * 100);
		while (newSerialNumber == firstEngineFromCarArray.getSerialNumber()) {
			newSerialNumber = (int) (Math.random() * 100);
		}
		firstEngineFromCarArray.setSerialNumber(newSerialNumber);

		// Change SaList from first original car
		List<SA> newSaList = new ArrayList<SA>(firstOriginalCar.getSaList());
		if (!CollectionUtils.isEmpty(newSaList)) {
			newSaList.get(0).setSaId(11);
			firstOriginalCar.setSaList(newSaList);
		}

		Assert.assertNotEquals("Not a deep copy! Unexpected Engine serialnumber",
				firstOriginalCar.getEngine().getSerialNumber(), firstClonedCar.getEngine().getSerialNumber());
		Assert.assertNotEquals("SaList is not a deep copy", firstOriginalCar.getSaList(), firstClonedCar.getSaList());
	}

	@Before
	public void init() {
		stopWatch = new StopWatch();
		TestUtils.initializeExampleObjectArray(testCarArray);
	}

	@After
	public void resetTimer() {
		stopWatch.reset();
	}

	@Test
	public void test_CopyConstructor() {

		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		testname = "Copy-Constructor";
		TestUtils.printStartingTestMessage(testname);

		// Starting cloning -> Start StopWatch
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = new Car(car);
		}
		// End of clone
		stopWatch.stop();

		checkTestResult(clonedCarArray);
		TestUtils.printStopWatchResult(testname, stopWatch.toString());
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

		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
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

		checkTestResult(clonedCarArray);
		TestUtils.printStopWatchResult(testname, stopWatch.toString());
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
		testname = "Serialization";
		TestUtils.printStartingTestMessage(testname);

		// Starting cloning
		stopWatch.start();
		int j = 0;
		for (Car exampleObject : testCarArray) {
			clonedCarArray[j++] = Serialize.deepCopyWithSerialization(exampleObject);
		}

		// End of clone
		stopWatch.stop();

		checkTestResult(clonedCarArray);

		TestUtils.printStopWatchResult(testname, stopWatch.toString());
	}

	@Test
	public void test_SerializationUtils() {

		// Setup: Create 1000 objects
		Car[] clonedCarArray = new Car[TestUtils.TESTELEMENT_AMOUNT];
		testname = "Serialization Utils";
		TestUtils.printStartingTestMessage(testname);

		// Starting cloning
		stopWatch.start();
		int j = 0;
		for (Car car : testCarArray) {
			clonedCarArray[j++] = SerializationUtil.copyWithSerializationutil(car);
		}
		// End of clone
		stopWatch.stop();

		checkTestResult(clonedCarArray);
		TestUtils.printStopWatchResult(testname, stopWatch.toString());
	}

}
