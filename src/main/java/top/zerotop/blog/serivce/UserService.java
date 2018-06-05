package top.zerotop.blog.serivce;

import top.zerotop.blog.entity.Admin;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月5日下午11:00:32
 */
public interface UserService {

	Admin selectByUsernameAndPassword(String username, String password);
}
