package top.zerotop.blog.controller.condition;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "查询条件")
public class PageCondition {

    @ApiModelProperty(value = "当前页数", position = 0)
    private int current;

    @ApiModelProperty(value = "数据数量", position = 1)
    private int size;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
