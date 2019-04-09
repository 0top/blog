package top.zerotop.blog.releam;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.Assert;
import top.zerotop.blog.db.mapper.AdminMapper;
import top.zerotop.blog.db.mapper.UserRoleMapper;
import top.zerotop.blog.db.model.Admin;
import top.zerotop.blog.db.model.UserRole;
import top.zerotop.blog.util.EncryptUtils;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月23日下午11:14:50
 */
public class AdminRealm extends AuthorizingRealm {
    public static final transient Logger logger = LoggerFactory.getLogger(AdminRealm.class);

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public String getName(){
		return "AdminRealm";
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info(" =====> user authorization");
		
		 Admin admin = (Admin) principals.getPrimaryPrincipal();
		System.out.println(admin.toString());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<>();

		for(UserRole userRole: userRoleMapper.listUserRoleByUserId(admin.getId())){
			roles.add(userRole.getRoleName());
		}
		
		info.setRoles(roles);
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info(token.getPrincipal() + " : 开始认证 ");
		
		String username = token.getPrincipal().toString();
		String password = String.valueOf((char[])token.getCredentials());

        Admin admin = adminMapper.selectByUsernameAndPassword(username, EncryptUtils.MD5(password));
		if (null != admin) {
			logger.info(String.format("admin: [%s] 认证成功", admin.getUsername()));

			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin, password, getName());
			return authenticationInfo;
		} else {
			throw new AuthenticationException("认证失败");
		}
	}
}
