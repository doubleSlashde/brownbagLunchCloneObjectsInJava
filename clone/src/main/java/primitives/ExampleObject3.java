package primitives;

import java.io.Serializable;

public class ExampleObject3 implements Serializable {

	private int number;

	// Default constructor
	public ExampleObject3() {
	}

	// Copy constructor
	public ExampleObject3(final ExampleObject3 primitiveObject) {
		this.number = primitiveObject.number;
	}

	// Parameter constructor
	public ExampleObject3(final int number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExampleObject3 other = (ExampleObject3) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
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
