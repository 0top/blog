package top.zerotop.blog.util;

import org.dozer.DozerBeanMapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertToDTO {
     private static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public static <T> List<T> convertToDTO(Collection<?> collection, Class<T> tClass) {
        if (!CollectionUtils.isEmpty(collection)) {
            List<T> lists = new ArrayList<>();
            collection.forEach(x -> {
                T t = dozerBeanMapper.map(x, tClass);
                lists.add(t);
            });
            return lists;
        } else {
            return new ArrayList<>();
        }
    }
}
