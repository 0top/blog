package top.zerotop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import top.zerotop.blog.web.condition.PageCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:zerotop  date:2019/10/9
 */
public class PageInfo {
    private static Logger logger = LoggerFactory.getLogger(PageInfo.class);

    public static <T> List<T> getPage(List<T> list, PageCondition condition) {
        int page = condition.getCurrent() * condition.getSize();
        int pageSize = page + condition.getSize();

        if (CollectionUtils.isEmpty(list) || page < 0 || pageSize < 0) {
            logger.warn("get page param error: start:[{}], end:[{}]", page, pageSize);
            return new ArrayList<>();
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        if (startIndex < list.size()) {
            return list.subList(startIndex, endIndex >= list.size() ? list.size() - 1 : endIndex);
        }
        return new ArrayList<>();
    }
}
