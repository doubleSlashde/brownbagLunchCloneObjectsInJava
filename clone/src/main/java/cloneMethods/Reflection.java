package cloneMethods;

import java.lang.reflect.Field;

public class Reflection {

	protected static <T, V> void copyFieldByFieldWithReflection(T entity, T newEntity, Class<?> clazz)
			throws IllegalAccessException, InstantiationException {
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

	public static <T> T deepCopyWithReflection(T entity) throws IllegalAccessException, InstantiationException {
		Class<?> clazz = entity.getClass();
		T newEntity = (T) entity.getClass().newInstance();

		while (clazz != null) {
			copyFieldByFieldWithReflection(entity, newEntity, clazz);
			clazz = clazz.getSuperclass();
		}

		return newEntity;
	}

}