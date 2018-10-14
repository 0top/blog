package top.zerotop.blog.releam;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import top.zerotop.blog.db.mapper.AdminMapper;
import top.zerotop.blog.db.mapper.UserRoleMapper;
import top.zerotop.blog.db.model.Admin;
import top.zerotop.blog.domain.UserRole;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月23日下午11:14:50
 */
public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public String getName(){
		return "AdminRealm";
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权");
		
		 Admin admin = (Admin) principals.getPrimaryPrincipal();
		
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
		System.out.println("认证");
		
		String username = token.getPrincipal().toString();
		String password = token.getCredentials().toString();
		
		Admin  admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		
		admin = adminMapper.selectByUsernameAndPassword(username, password);


		if(null == admin){
			throw new UnknownAccountException();
		}
		else{
			
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo( username, password, getName());
            return authenticationInfo;
            
		}
	}

}
