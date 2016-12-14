package exampleObjects;

import java.io.Serializable;

public class Car implements Serializable, Cloneable {

	private int id;
	private Engine engine;

	public Car() {
	}

	public Car(Car anotherCar) {
		this.id = anotherCar.getId();
		Engine engine = new Engine();
		engine.setSerialNumber(anotherCar.getEngine().getSerialNumber());
		this.engine = engine;

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setManufacturerNumber(anotherCar.getEngine().getManufacturer().getManufacturerNumber());

		engine.setManufacturer(manufacturer);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Car cloneCar() throws CloneNotSupportedException {
		return (Car) this.clone();
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
