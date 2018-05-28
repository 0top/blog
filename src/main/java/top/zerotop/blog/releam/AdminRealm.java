package top.zerotop.blog.releam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import top.zerotop.blog.dao.AdminMapper;
import top.zerotop.blog.entity.Admin;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月23日下午11:14:50
 */
public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private AdminMapper adminDao;
	
	public String getName(){
		return "AdminRealm";
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证");
		
		String username = token.getPrincipal().toString();
		String password = token.getCredentials().toString();
		
		Admin  admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		
		admin = adminDao.selectByUsernameAndPassword(username, password);


		if(null == admin){
			throw new UnknownAccountException();
		}
		else{
			
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo( username, password, getName());
            return authenticationInfo;
            
		}
	}

}
