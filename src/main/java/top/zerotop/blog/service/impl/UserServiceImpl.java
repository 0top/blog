package top.zerotop.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zerotop.blog.db.mapper.AdminMapper;
import top.zerotop.blog.db.model.Admin;
import top.zerotop.blog.service.UserService;
import top.zerotop.blog.util.EncryptUtil;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月5日下午11:01:33
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public Admin selectByUsernameAndPassword(String username, String password) {
	    password = EncryptUtil.MD5(password);

		return adminMapper.selectByUsernameAndPassword(username, password);
	}

	@Override
	public int addUser(Admin admin) {
		admin.setCode(EncryptUtil.MD5(admin.getUsername()+System.currentTimeMillis()));
		admin.setPassword(EncryptUtil.MD5(admin.getPassword()));
		return adminMapper.insertAdmin(admin);
	}


}
