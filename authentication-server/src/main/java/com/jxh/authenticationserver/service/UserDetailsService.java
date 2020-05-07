package com.jxh.authenticationserver.service;

import com.jxh.oauth2config.constants.AuthoritiesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户信息获取
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    Logger log = LoggerFactory.getLogger(UserDetailsService.class);
    /**
     * 通过 Username 加载用户详情
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("jingxiaohu")) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode("111111");
            UserDetails userDetails = new User("jingxiaohu",
                    password,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(AuthoritiesEnum.USER.getRole()));
            //TODO 打印下用户信息
            log.info(userDetails.toString());
            return userDetails;
        }
        return null;
    }
}
