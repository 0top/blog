package top.zerotop.blog.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月28日下午10:20:26
 */
public class ReMap {

	public static String ResultMap(int errno, String msg, Object data){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errno", errno);
		result.put("msg", msg);
		if(null != data){
			result.put("data", data);
		}
		return JSON.toJSONString(result);
	}
}
