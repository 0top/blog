package top.zerotop.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月16日下午11:51:28
 */
public class ReqJson {
	public static String Json(HttpServletRequest req) {
		StringBuffer jsonString = new StringBuffer();
		try {
			InputStream in = req.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;

			while ((iRead = buf.read(buffer)) != -1) {
				jsonString.append(new String(buffer, 0, iRead, "utf-8"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return jsonString.toString();
	}
}
