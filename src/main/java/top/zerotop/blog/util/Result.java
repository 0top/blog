package top.zerotop.blog.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月28日下午10:20:26
 */
@ApiModel(value = "返回结果")
public class Result {

    @ApiModelProperty(value = "返回代码", position = 0)
    private int code;

    @ApiModelProperty(value = "返回消息", position = 1)
    private String msg;

    @ApiModelProperty(value = "数据内容", position = 2)
	private Object content;

    public Result(Object content) {
        this.code = 200;
        this.msg = "请求成功";
        this.content = content;
    }

	public Result(String msg, Object content) {
        this.code = 200;
        this.msg = msg;
		this.content = content;
	}

	public Result(int code, String msg, Object content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
