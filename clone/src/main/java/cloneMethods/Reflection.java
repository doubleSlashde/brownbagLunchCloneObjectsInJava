package cloneMethods;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

public class Reflection {

	protected static <T, V, K> void copyFieldByFieldWithReflection(T entity, T newEntity, Class<?> clazz)
			throws IllegalAccessException, InstantiationException {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (!field.getType().isPrimitive() && !field.getType().isInterface()) {
				Class<?> innerClazz = field.getType();
				V innerEntity = (V) field.get(entity);
				V newInnerEntity = (V) innerClazz.newInstance();
				field.set(newEntity, newInnerEntity);
				copyFieldByFieldWithReflection(innerEntity, newInnerEntity, innerClazz);
				continue;
			}
			if (field.getType() == List.class) {
				List originalList = (List) field.get(entity);
				List listCopy = new ArrayList();
				for (int i = 0; i < originalList.size(); i++) {
					listCopy.add(new Object());
				}
				copyList(listCopy, originalList);
				field.set(newEntity, listCopy);
				continue;
			}
			Object value = field.get(entity);
			field.set(newEntity, value);
		}
	}

	private static <T> void copyList(List<? super T> dest, List<? extends T> src)
			throws IllegalAccessException, InstantiationException {
		int srcSize = src.size();
		if (srcSize > dest.size()) {
			throw new IndexOutOfBoundsException("Source does not fit in dest");
		}
		if (src instanceof RandomAccess && dest instanceof RandomAccess) {
			for (int i = 0; i < srcSize; i++) {
				T innerEntity = src.get(i);
				T copy = deepCopyWithReflection(innerEntity);
				dest.set(i, copy);
			}
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