package com.ctrip.blog.realm;

import com.ctrip.blog.pojo.Blogger;
import com.ctrip.blog.service.BloggerService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by qiuhl on 2017/5/24.
 */
public class MyRealm extends AuthorizingRealm{

    @Resource
    private BloggerService bloggerService;
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 对当前登录的用户进行身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String) authenticationToken.getPrincipal();
        Blogger blogger = bloggerService.getByUsername(username);
        if(null != blogger){
            //把当前用户存到session中
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
            //把从数据库中查询出来的博主信息放到authcInfo中返回给Shiro
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    blogger.getUsername(), blogger.getPassword(), "MyRealm");
            return authcInfo;
        }
        return null;
    }
}
