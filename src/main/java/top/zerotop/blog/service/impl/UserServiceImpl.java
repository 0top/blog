package top.zerotop.blog.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import top.zerotop.blog.data.mapper.AdminMapper;
import top.zerotop.blog.data.model.Admin;
import top.zerotop.blog.service.UserService;
import top.zerotop.utils.EncryptUtils;
import top.zerotop.blog.web.Request.AdminRequest;
import top.zerotop.exception.BlogException;
import top.zerotop.exception.UserAccountException;

import java.time.LocalDateTime;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月5日下午11:01:33
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private DozerBeanMapper dozerMapper;
	
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
	public int insertAdmin(AdminRequest adminRequest) throws BlogException {
		Admin admin = dozerMapper.map(adminRequest, Admin.class);

		Admin admin1 = adminMapper.selectAdmin(admin.getUsername());
		if (null != admin1) {
            throw new UserAccountException("用户名已存在。");
        }

		admin.setCode(EncryptUtils.MD5(admin.getUsername()+System.currentTimeMillis()));
		admin.setPassword(EncryptUtils.MD5(admin.getPassword()));
		admin.setGmtCreate(LocalDateTime.now().toString());
		admin.setGmtModified(LocalDateTime.now().toString());

		return adminMapper.insertAdmin(admin);
	}
}
