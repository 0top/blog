package top.zerotop.blog.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import top.zerotop.blog.util.Result;


/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月21日下午8:41:12
 */
public abstract class BaseController {
	
	@ExceptionHandler({Exception.class})
	public @ResponseBody Result unknowException(Exception e){

		e.printStackTrace();
		return Result.error(500, "请求出错...");
	}
}
