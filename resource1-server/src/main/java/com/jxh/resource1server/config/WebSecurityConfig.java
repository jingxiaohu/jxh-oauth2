package com.jxh.resource1server.config;

import com.jxh.oauth2config.config.ResServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 资源服务器访问权限配置
 */
@Configuration
public class WebSecurityConfig extends ResServerConfig {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/res","/res2/res")
                .access("#oauth2.hasScope('read') and hasRole('USER')");
    }

}
