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
import top.zerotop.blog.data.mapper.BlogAdminMapper;
import top.zerotop.blog.data.mapper.UserRoleMapper;
import top.zerotop.blog.data.model.BlogAdmin;
import top.zerotop.blog.data.model.UserRole;
import top.zerotop.utils.EncryptUtils;
import top.zerotop.utils.JsonUtils;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月23日下午11:14:50
 */
public class AdminRealm extends AuthorizingRealm {
    public static final transient Logger logger = LoggerFactory.getLogger(AdminRealm.class);

    @Autowired
    private BlogAdminMapper blogAdminMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public String getName() {
        return "AdminRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info(" ========> user authorization <======== ");

        BlogAdmin blogAdmin = (BlogAdmin) principals.getPrimaryPrincipal();
        logger.info("===>" + JsonUtils.toJson(blogAdmin));

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();

        List<UserRole> userRoles = userRoleMapper.listUserRoleByUserId(blogAdmin.getId());
        if (!CollectionUtils.isEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                roles.add(userRole.getRoleName());
            }
            authorizationInfo.setRoles(roles);
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("========> " + token.getPrincipal() + " 开始认证 <======== ");

        String username = token.getPrincipal().toString();
        String password = String.valueOf((char[]) token.getCredentials());

        BlogAdmin blogAdmin = blogAdminMapper.selectByUsernameAndPassword(username, EncryptUtils.MD5(password));
        if (null != blogAdmin) {
            logger.info(String.format("admin: [%s] 认证成功", username));

            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogAdmin, password, getName());
            return authenticationInfo;
        } else {
            logger.error(token.getPrincipal() + " 用户认证失败<===");
            throw new AuthenticationException(token.getPrincipal() + "认证失败");
        }
    }
}
