package top.zerotop.blog.serivce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zerotop.blog.dao.AdminMapper;
import top.zerotop.blog.domain.Admin;
import top.zerotop.blog.serivce.UserService;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月5日下午11:01:33
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AdminMapper adminDao;
	
	@Override
	public Admin selectByUsernameAndPassword(String username, String password) {
		return adminDao.selectByUsernameAndPassword(username, password);
	}

}
