package primitives;

/**
 * Object holding only primitive types.
 * 
 * @author straeger
 *
 */
public class ObjectHoldingPrimitives {

	private int number;
	private char letter;

	// Default constructor
	public ObjectHoldingPrimitives() {
		this.number = 1;
		this.letter = 'D';
	}

	// Parameter constructor
	public ObjectHoldingPrimitives(final int number, final char letter) {
		this.number = number;
		this.letter = letter;
	}

	// Copy constructor 
	public ObjectHoldingPrimitives(final ObjectHoldingPrimitives primitiveObject) {
		this.number = primitiveObject.number;
		this.letter = primitiveObject.letter;
	}

}
