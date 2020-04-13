package top.zerotop.blog.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import top.zerotop.blog.data.mapper.BlogAdminMapper;
import top.zerotop.blog.data.model.BlogAdmin;
import top.zerotop.blog.service.UserService;
import top.zerotop.global.exception.UserHasExistException;
import top.zerotop.utils.EncryptUtils;
import top.zerotop.blog.web.Request.AdminRequest;
import top.zerotop.global.exception.BlogException;

import java.time.LocalDateTime;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月5日下午11:01:33
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BlogAdminMapper blogAdminMapper;
	@Autowired
	private DozerBeanMapper dozerMapper;
	
	@Override
	public BlogAdmin selectByUsernameAndPassword(String username, String password) {
		if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
			password = EncryptUtils.MD5(password);
			return blogAdminMapper.selectByUsernameAndPassword(username, password);
		}
	    return null;
	}

	@Override
	public BlogAdmin selectAdminByUserName(String username) {
		if (StringUtils.hasText(username)) {
			return blogAdminMapper.selectAdminByUserName(username);
		}
		return null;
	}

	@Override
	public int insertAdmin(AdminRequest adminRequest) throws BlogException {
		BlogAdmin blogAdmin = dozerMapper.map(adminRequest, BlogAdmin.class);

		BlogAdmin blogAdmin1 = blogAdminMapper.selectAdminByUserName(blogAdmin.getUsername());
		if (null != blogAdmin1) {
            throw new UserHasExistException("用户名已存在。");
        }

		blogAdmin.setCode(EncryptUtils.MD5(blogAdmin.getUsername()+System.currentTimeMillis()));
		blogAdmin.setPassword(EncryptUtils.MD5(blogAdmin.getPassword()));
		blogAdmin.setGmtCreate(LocalDateTime.now().toString());
		blogAdmin.setGmtModified(LocalDateTime.now().toString());

		return blogAdminMapper.insertBlogAdmin(blogAdmin);
	}
}
