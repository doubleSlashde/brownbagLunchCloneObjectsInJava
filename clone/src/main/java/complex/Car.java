package complex;

import java.io.Serializable;

import primitives.Manufacturer;

public class Car implements Serializable {

	/**
	 * The id of the car.
	 */
	private int id;

	/**
	 * The engine of the car.
	 */
	private Engine engine;

	// Default Constructor
	public Car() {
	}

	// Copy-Constructor with boolean for decide whether a deep or shallow copy
	// is needed
	public Car(final boolean deepCopy, Car carToCopy) {
		if (deepCopy == true) {
			this.id = carToCopy.getId();
			Engine eninge = new Engine();
			eninge.setSerialNumber(carToCopy.getEngine().getSerialNumber());
			this.engine = eninge;

			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setManufacturerNumber(carToCopy.getEngine().getManufacturer().getManufacturerNumber());

			eninge.setManufacturer(manufacturer);
		} else {
			this.id = carToCopy.getId();
			this.engine = carToCopy.getEngine();
		}
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
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		if (engine == null) {
			if (other.engine != null)
				return false;
		} else if (!engine.equals(other.engine))
			return false;
		return true;
	}

	public Engine getEngine() {
		return engine;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		return result;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Car [" //
				+ "\nId=" + id + ", " //
				+ "\nEngine=" + engine + //
				"]"; //
	}

}
