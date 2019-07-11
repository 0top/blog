package top.zerotop.utils;

import org.dozer.DozerBeanMapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertUtils {
    private static DozerBeanMapper dozerMapper = new DozerBeanMapper();

    public static <T> List<T> convertToDTO(Collection<?> collection, Class<T> tClass) {
        List<T> lists = new ArrayList<>();
        if (CollectionUtils.isEmpty(collection)) {
            return lists;
        }
        collection.forEach(x -> {
            T t = dozerMapper.map(x, tClass);
            lists.add(t);
        });
        return lists;
    }
}
