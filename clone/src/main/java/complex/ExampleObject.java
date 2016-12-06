package complex;

import java.io.Serializable;

public class ExampleObject implements Cloneable, Serializable {

	private int number;

	private ExampleObject2 otherObject;

	// Default Constructor
	public ExampleObject() {
		this.setNumber(1);
		this.setExampleObject2(new ExampleObject2());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExampleObject other = (ExampleObject) obj;
		if (number != other.number)
			return false;
		if (otherObject == null) {
			if (other.otherObject != null)
				return false;
		} else if (!otherObject.equals(other.otherObject))
			return false;
		return true;
	}

	public int getNumber() {
		return number;
	}

	public ExampleObject2 getOtherObject() {
		return otherObject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((otherObject == null) ? 0 : otherObject.hashCode());
		return result;
	}

	public void setExampleObject2(ExampleObject2 otherObject) {
		this.otherObject = otherObject;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ExampleObject [" //
				+ "\nnumber=" + number + ", " //
				+ "\notherObject=" + otherObject + //
				"]"; //
	}

}
