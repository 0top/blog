package top.zerotop.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import top.zerotop.blog.db.mapper.AdminMapper;
import top.zerotop.blog.db.model.Admin;
import top.zerotop.blog.service.UserService;
import top.zerotop.blog.util.EncryptUtils;

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
		if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
			return null;
		}
	    password = EncryptUtils.MD5(password);
		return adminMapper.selectByUsernameAndPassword(username, password);
	}

	@Override
	public Admin selectAdminByUserName(String username) {
		return adminMapper.selectAdmin(username);
	}

	@Override
	public int insertAdmin(Admin admin) {
		admin.setCode(EncryptUtils.MD5(admin.getUsername()+System.currentTimeMillis()));
		admin.setPassword(EncryptUtils.MD5(admin.getPassword()));

		return adminMapper.insertAdmin(admin);
	}


}
