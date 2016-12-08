package cloneExample;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

import complex.Car;
import complex.Engine;
import primitives.Manufacturer;

/**
 * Class holding different methods for cloning objects.
 * 
 * @author straeger
 *
 */
public class CloneUtils {

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
	// TODO: Why do this method only copy shallow?
	public static Car copyCar(Car entity) throws IllegalAccessException, InstantiationException {
		Class<?> clazz = entity.getClass();
		Car newEntity = entity.getClass().newInstance();

		while (clazz != null) {
			copyFieldByFieldFromCarPerReflection(entity, newEntity, clazz);
			clazz = clazz.getSuperclass();
		}

		return newEntity;
	}

	// TODO: Make method generic. Therefore: Check all fields. If Object -> get
	// values and set in a new entity
	private static void copyFieldByFieldFromCarPerReflection(Car entity, Car newEntity, Class<?> clazz)
			throws IllegalAccessException {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (field.getType().equals(Engine.class)) {
				Engine value = (Engine) field.get(entity);
				Engine newEngine = new Engine();
				newEngine.setSerialNumber(value.getSerialNumber());
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setManufacturerNumber(value.getManufacturer().getManufacturerNumber());
				newEngine.setManufacturer(manufacturer);
				field.set(newEntity, newEngine);
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
