package top.zerotop.utils;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    private static DozerBeanMapper dozerMapper = new DozerBeanMapper();

    /**
     * 转对象为list
     * @param collection
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> convertToDTOs(Collection<?> collection, Class<T> tClass) {
        List<T> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(collection)) {
            return result;
        }
        try {
            collection.forEach(x -> {
                T t = dozerMapper.map(x, tClass);
                result.add(t);
            });
        } catch (Exception e) {
            logger.warn("convert to list failed. exception: {}", e.getMessage());
        }
        return result;
    }
}
