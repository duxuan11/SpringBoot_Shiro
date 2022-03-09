package com.duxuan.config;

import com.duxuan.pojo.User;
import com.duxuan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/*自定类 继承该类

* */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Override
    /*授权*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权AuthorizationInfo");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //(AuthenticationToken 对其加了密) 将token转成 UsernamePasswordToken
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //到数据库中查询
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null){ //查不到人
            return null;
        }
        //传递Session来告知是否
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("logined",user);

        //第一个参数是对象user 第二个是密码 第三个是账号 既然我们查询查出来user 只需验证密码即可 它会自己与token里的密码比对
        return new SimpleAuthenticationInfo("",user.getPwd(),"");

    }
}
