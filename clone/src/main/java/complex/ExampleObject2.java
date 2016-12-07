package complex;

import java.io.Serializable;

import primitives.ExampleObject3;

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
	}

	// Copy constructor
	public ExampleObject2(final ExampleObject2 primitiveObject) {
		this.number = primitiveObject.number;
	}

	// Parameter constructor
	public ExampleObject2(final int number) {
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
		ExampleObject2 other = (ExampleObject2) obj;
		if (exampleObject3 == null) {
			if (other.exampleObject3 != null)
				return false;
		} else if (!exampleObject3.equals(other.exampleObject3))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	public ExampleObject3 getExampleObject3() {
		return exampleObject3;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exampleObject3 == null) ? 0 : exampleObject3.hashCode());
		result = prime * result + number;
		return result;
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
