package top.zerotop.blog.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月28日下午10:20:26
 */
@ApiModel(value = "返回结果")
public class Result<Content> {
    public static Result<Boolean> SUCCESS = new Result<>(true);

    @ApiModelProperty(value = "返回代码", position = 0)
    private int code = 200;

    @ApiModelProperty(value = "返回消息", position = 1)
    private String msg = "请求成功";

    private boolean success = false;

    @ApiModelProperty(value = "数据内容", position = 2)
	private Content content;

    public Result() {
    }


    public Result(boolean success) {
        this.success = true;
    }

	public Result(String msg, Content content) {
        this.msg = msg;
		this.content = content;
	}

	public static <Content> Result<Content> error(int code, String msg) {
        Result<Content> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }

    public static <Content> Result<Content> make(Content content) {
        Result<Content> result = new Result<Content>();
        result.setContent(content);
        return result;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
