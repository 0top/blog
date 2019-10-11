package top.zerotop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:zerotop  date:2019/10/9
 */
public class PageInfo {
    private static Logger logger = LoggerFactory.getLogger(PageInfo.class);

    public static <T> List<T> getPage(List<T> list, int start, int end) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        if (start < 0 || end < 0 || start > end) {
            logger.error("get page param error: start:[{}], end:[{}]", start, end);
            return new ArrayList<>();
        }
        if (start >= list.size()) {
            return list;
        }
        return list.subList(start, end >= list.size() ? list.size() - 1 : end);
    }
}
