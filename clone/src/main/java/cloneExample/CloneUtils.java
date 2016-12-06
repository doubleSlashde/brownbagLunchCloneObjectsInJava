package cloneExample;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

public class CloneUtils {

	public static <T> T copyWithReflection(T entity) throws IllegalAccessException, InstantiationException {
		Class<?> clazz = entity.getClass();
		T newEntity = (T) entity.getClass().newInstance();

		while (clazz != null) {
			copyFieldByFieldWithReflection(entity, newEntity, clazz);
			clazz = clazz.getSuperclass();
		}

		return newEntity;
	}

	private static <T> T copyFieldByFieldWithReflection(T entity, T newEntity, Class<?> clazz) throws IllegalAccessException {
		// List<Field> fields = new ArrayList<>();
		Field[] declaredFields = clazz.getDeclaredFields();
		// for (Field field : declaredFields) {
		// fields.add(field);
		// }
		for (Field field : declaredFields) {
			field.setAccessible(true);
			field.set(newEntity, field.get(entity));
		}
		return newEntity;
	}

	public static <T extends Serializable> T copyWithSerialization(T objectToCopy) throws Exception {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(objectToCopy);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bais);
			return (T) in.readObject();
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

}
