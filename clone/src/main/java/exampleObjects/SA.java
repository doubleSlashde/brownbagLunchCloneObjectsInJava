package exampleObjects;

import java.io.Serializable;

public class SA implements Serializable {

	private int saId;

	public SA() {
	}

	public SA(int saId) {
		this.saId = saId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SA other = (SA) obj;
		if (saId != other.saId)
			return false;
		return true;
	}

	public int getSaId() {
		return saId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + saId;
		return result;
	}

	public void setSaId(int saId) {
		this.saId = saId;
	}

	@Override
	public String toString() {
		return "SA [" + "\nsaId=" + saId + "]";
	}

}
