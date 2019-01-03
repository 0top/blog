package top.zerotop.blog.util;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertToDTO {
     private static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public static <T> List<T> convertObjectToDTO(Collection<?> collection, Class<T> tClass) {
        if (org.springframework.util.CollectionUtils.isEmpty(collection)) {
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
