package complex;

import primitives.ObjectHoldingPrimitives;

/**
 * Object holding other objects inside.
 * 
 * @author straeger
 *
 */
public class ObjectHoldingOtherObject {

	private String name;
	private ObjectHoldingPrimitives otherObject;

	// Default Constructor
	public ObjectHoldingOtherObject() {
		this.name = "defaultName";
		this.otherObject = new ObjectHoldingPrimitives();
	}

	// Copy constructor
	public ObjectHoldingOtherObject(final ObjectHoldingOtherObject otherObjectOfSameType) {
		this.name = otherObjectOfSameType.name;
		this.otherObject = otherObjectOfSameType.otherObject;
	}

	// Parameter Constructor
	public ObjectHoldingOtherObject(final String name, final ObjectHoldingPrimitives otherObject) {
		this.name = name;
		// NOTE: Only the reference is aligned to this.otherObject
		this.otherObject = otherObject;
	}

}
