package cloneExample;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Class holding different methods for cloning objects.
 * 
 * @author straeger
 *
 */
public class CloneUtils {

	private static <T, V> void copyFieldByFieldWithReflection(T entity, T newEntity, Class<?> clazz) throws Exception {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (!field.getType().isPrimitive()) {
				Class<?> innerClazz = field.getType();
				V innerEntity = (V) field.get(entity);
				V newInnerEntity = (V) field.getType().newInstance();
				field.set(newEntity, newInnerEntity);
				copyFieldByFieldWithReflection(innerEntity, newInnerEntity, innerClazz);
				continue;
			}
			Object value = field.get(entity);
			field.set(newEntity, value);
		}
	}

	public static <T> T copyWithBeanUtils(T entity) throws Exception {
		T newEntity;
		newEntity = (T) BeanUtils.cloneBean(entity);
		return newEntity;
	}

	/**
	 * Method for Deep-Copying a Object via Reflection.
	 * 
	 * @param entity
	 *            the Entity to be cloned
	 * @return the cloned object
	 * @throws IllegalAccessException
	 *             the IllegalAccessException
	 * @throws InstantiationException
	 *             the InstantiationException
	 */
	// TODO: method only copy shallow.
	// TEMPORARY SOLUTION: Temporary fixed by removing the generics from the
	// copyFieldByField...-Method and replace it with specific copy for car
	// object.
	public static <T> T copyWithReflection(T entity) throws Exception {
		Class<?> clazz = entity.getClass();
		T newEntity = (T) entity.getClass().newInstance();

		while (clazz != null) {
			copyFieldByFieldWithReflection(entity, newEntity, clazz);
			clazz = clazz.getSuperclass();
		}

		return newEntity;
	}

	/**
	 * Method for Deep-Copying a Object that implements the
	 * Serializable-Interface via Serialization.
	 * 
	 * @param objectToCopy
	 *            the objectToCopy
	 * @return the copy of the object
	 * @throws Exception
	 *             the Exception
	 */
	public static <T extends Serializable> T copyWithSerialization(T objectToCopy) throws Exception {
		try {
			byte[] serializedObject = serialize(objectToCopy);
			return deserialize(serializedObject);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	private static <T> T deserialize(final byte[] serializedObject) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(serializedObject);
		ObjectInputStream in = new ObjectInputStream(bais);
		return (T) in.readObject();
	}

	private static <T extends Serializable> byte[] serialize(T objectToCopy) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(baos);
		out.writeObject(objectToCopy);
		return baos.toByteArray();
	}

}
