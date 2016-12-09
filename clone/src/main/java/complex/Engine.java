package complex;

import java.io.Serializable;

import primitives.Manufacturer;

/**
 * Object holding only primitive types.
 * 
 * @author straeger
 *
 */
public class Engine implements Serializable {

	/**
	 * The serialNumber of the engine.
	 */
	private int serialNumber;

	/**
	 * The manufacturer of the engine.
	 */
	private Manufacturer manufacturer;

	// Default constructor
	public Engine() {
	}

	// Copy constructor
	public Engine(final Engine engine) {
		this.serialNumber = engine.serialNumber;
	}

	// Parameter constructor
	public Engine(final int serialNumber) {
		this.serialNumber = serialNumber;
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
		Engine other = (Engine) obj;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (serialNumber != other.serialNumber)
			return false;
		return true;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + serialNumber;
		return result;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return "Engine [" //
				+ "\nSerialNumber=" + serialNumber //
				+ "\nManufacturer=" + manufacturer //
				+ "]";
	}

}
