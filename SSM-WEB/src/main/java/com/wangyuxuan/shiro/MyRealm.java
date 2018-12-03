package com.wangyuxuan.shiro;

import com.wangyuxuan.pojo.T_user;
import com.wangyuxuan.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/3 15:57
 * @Description:
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     *
     * 功能描述: 用于权限的认证。
     *
     * @param: [principals]
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @auther: wangyuxuan
     * @date: 2018/12/3 16:09
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleName = userService.findRoles(username);
        Set<String> permissions = userService.findPermissions(username);
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     *
     * 功能描述: 首先执行这个登录验证
     *
     * @param: [token]
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * @auther: wangyuxuan
     * @date: 2018/12/3 16:09
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户账号
        String username = token.getPrincipal().toString();
        T_user user = userService.findUserByUsername(username);
        if(user != null){
            //将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数随便放一个就行了。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "wangyuxuan");
            return authenticationInfo;
        }else {
            return null;
        }
    }
}
