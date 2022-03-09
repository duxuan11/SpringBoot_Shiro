package com.duxuan.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {
    //过滤请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilter(@Qualifier("securityManager") DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
       bean.setSecurityManager(securityManager);

        Map<String,String>map = new LinkedHashMap<>();
        map.put("/user/*","authc"); //包含authc 和 user 前者是认证过，后者是登录过，如果开启了Readmemberme功能的话，后者也是可以通过的，而前者通过不了。
        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    //创建安全管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Autowired MyShiroRealm myShiroRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    //创建域
    @Bean
    public MyShiroRealm getMyShiroRealm(){
        return new MyShiroRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
