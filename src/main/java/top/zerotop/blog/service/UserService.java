package top.zerotop.blog.service;

import top.zerotop.blog.domain.Admin;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月5日下午11:00:32
 */
public interface UserService {

	Admin selectByUsernameAndPassword(String username, String password);
}