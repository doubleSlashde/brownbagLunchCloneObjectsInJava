package primitives;

import java.io.Serializable;

public class Manufacturer implements Serializable {
	private int manufacturerNumber;

	public Manufacturer() {
	}

	public Manufacturer(final int manufacturerNumber) {
		this.manufacturerNumber = manufacturerNumber;
	}

	public Manufacturer(final Manufacturer anotherManufacturer) {
		this.manufacturerNumber = anotherManufacturer.manufacturerNumber;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		if (manufacturerNumber != other.manufacturerNumber)
			return false;
		return true;
	}

	public int getManufacturerNumber() {
		return manufacturerNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + manufacturerNumber;
		return result;
	}

	public void setManufacturerNumber(int manufacturerNumber) {
		this.manufacturerNumber = manufacturerNumber;
	}

	@Override
	public String toString() {
		return "Manufacturer [" //
				+ "\nManufacturernumber=" + manufacturerNumber + //
				"]";
	}

}
