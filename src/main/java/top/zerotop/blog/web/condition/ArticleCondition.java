package top.zerotop.blog.web.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "文章查询条件")
public class ArticleCondition extends PageCondition {

    @ApiModelProperty(value = "模糊查询", position = 0)
    private String searchString;

    @ApiModelProperty(value = "默认排序字段", position = 1)
    private String orderBy;

    @ApiModelProperty(value = "文章分类，| 分割", position = 2)
    private String category;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getCategory() {
        return category == null ? "" : category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ArticleCondition{" +
                "searchString='" + searchString + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
