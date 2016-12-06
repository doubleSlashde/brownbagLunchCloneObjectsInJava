package complex;

import java.io.Serializable;

public class ExampleObject implements Cloneable, Serializable {

	private int number;

	private ExampleObject2 otherObject;

	// Default Constructor
	public ExampleObject() {
		this.setNumber(1);
		this.setOtherObject(new ExampleObject2());
	}

	public int getNumber() {
		return number;
	}

	public ExampleObject2 getOtherObject() {
		return otherObject;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setOtherObject(ExampleObject2 otherObject) {
		this.otherObject = otherObject;
	}

	@Override
	public String toString() {
		return "ExampleObject [" //
				+ "\nnumber=" + number + ", " //
				+ "\notherObject=" + otherObject + //
				"]"; //
	}

}
