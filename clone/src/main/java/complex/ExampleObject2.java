package complex;

import java.io.Serializable;

/**
 * Object holding only primitive types.
 * 
 * @author straeger
 *
 */
public class ExampleObject2 implements Serializable {

	private int number;
	private ExampleObject3 exampleObject3;

	// Default constructor
	public ExampleObject2() {
		this.number = 1;
	}

	// Copy constructor
	public ExampleObject2(final ExampleObject2 primitiveObject) {
		this.number = primitiveObject.number;
	}

	// Parameter constructor
	public ExampleObject2(final int number) {
		this.number = number;
	}

	public ExampleObject3 getExampleObject3() {
		return exampleObject3;
	}

	public int getNumber() {
		return number;
	}

	public void setExampleObject3(ExampleObject3 exampleObject3) {
		this.exampleObject3 = exampleObject3;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ExampleObject2 [" //
				+ "\nnumber=" + number + //
				"]";
	}

}
