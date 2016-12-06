package complex;

import java.io.Serializable;

public class ExampleObject3 implements Serializable {

	private int number;

	// Default constructor
	public ExampleObject3() {
		this.number = 1;
	}

	// Copy constructor
	public ExampleObject3(final ExampleObject3 primitiveObject) {
		this.number = primitiveObject.number;
	}

	// Parameter constructor
	public ExampleObject3(final int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
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
