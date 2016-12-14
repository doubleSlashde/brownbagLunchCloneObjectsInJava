package cloneMethods;

import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

public class SerializationUtil {

	public static <T extends Serializable> T copyWithSerializationutil(T entity) {
		return (T) SerializationUtils.clone(entity);
	}

}
