package cloneMethods;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

public class Reflection {

	private static <T> void copy(List<? super T> dest, List<? extends T> src)
			throws IllegalAccessException, InstantiationException {
		int srcSize = src.size();
		if (srcSize > dest.size())
			throw new IndexOutOfBoundsException("Source does not fit in dest");

		if (src instanceof RandomAccess && dest instanceof RandomAccess) {
			for (int i = 0; i < srcSize; i++) {
				T innerEntity = src.get(i);
				Class<?> innerListClazz = innerEntity.getClass();
				T newEntity = (T) innerEntity.getClass().newInstance();
				copyFieldByFieldWithReflection(innerEntity, newEntity, innerListClazz);
				dest.set(i, newEntity);
			}
		}
		// } else {
		// ListIterator<? super T> di = dest.listIterator();
		// ListIterator<? extends T> si = src.listIterator();
		// for (int i = 0; i < srcSize; i++) {
		// di.next();
		// di.set(si.next());
		// }
		// }
	}

	protected static <T, V> void copyFieldByFieldWithReflection(T entity, T newEntity, Class<?> clazz)
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
				copy(listCopy, originalList);
				field.set(newEntity, listCopy);
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