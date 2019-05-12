package top.zerotop.blog.releam;

import java.util.HashSet;
import java.util.List;
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

import org.springframework.util.CollectionUtils;
import top.zerotop.blog.db.mapper.AdminMapper;
import top.zerotop.blog.db.mapper.UserRoleMapper;
import top.zerotop.blog.db.model.Admin;
import top.zerotop.blog.db.model.UserRole;
import top.zerotop.utils.EncryptUtils;
import top.zerotop.utils.JsonUtils;

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
		System.out.println("===>" + JsonUtils.toJson(admin));
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<>();

        List<UserRole> userRoles = userRoleMapper.listUserRoleByUserId(admin.getId());
        if (!CollectionUtils.isEmpty(userRoles)) {
            System.out.println("--->:" + JsonUtils.toJson(admin));
            for (UserRole userRole : userRoles) {
                roles.add(userRole.getRoleName());
            }
            System.out.println("--->" + JsonUtils.toJson(roles));
            info.setRoles(roles);
        }
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info(token.getPrincipal() + " : 开始认证 ");

		String username = token.getPrincipal().toString();
		String password = String.valueOf((char[])token.getCredentials());

		System.out.println("===>" + username + " passwd:" + EncryptUtils.MD5(password));


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
