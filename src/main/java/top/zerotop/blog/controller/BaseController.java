package top.zerotop.blog.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import top.zerotop.blog.util.ReMap;


/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月21日下午8:41:12
 */
public abstract class BaseController {
	
	@ExceptionHandler({Exception.class})
	public @ResponseBody String unknowException(){
		
		return ReMap.ResultMap(1, "请求出错", null);
	}
}
