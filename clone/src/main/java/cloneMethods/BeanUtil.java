package cloneMethods;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {

	public static <T> T shallowCopyWithBeanUtils(T entity) throws Exception {
		return (T) BeanUtils.cloneBean(entity);
	}

}
