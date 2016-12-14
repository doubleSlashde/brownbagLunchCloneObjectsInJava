package cloneMethods;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Class with different methods for copy an object deep or shallow.
 * 
 * @author straeger
 *
 */
public class Serialize {

	public static <T extends Serializable> T deepCopyWithSerialization(T objectToCopy) throws Exception {
		try {
			byte[] serializedObject = serialize(objectToCopy);
			return deserialize(serializedObject);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	protected static <T> T deserialize(final byte[] serializedObject) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(serializedObject);
		ObjectInputStream in = new ObjectInputStream(bais);
		return (T) in.readObject();
	}

	protected static <T extends Serializable> byte[] serialize(T objectToCopy) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(baos);
		out.writeObject(objectToCopy);
		return baos.toByteArray();
	}

}
