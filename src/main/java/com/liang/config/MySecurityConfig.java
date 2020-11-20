package com.liang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/11/16 20:34
 * @content
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        return new InMemoryTokenRepositoryImpl();
//    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 定制请求授权规则
        http.authorizeRequests().antMatchers("/", "/login").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启登录功能
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth/form")
                .defaultSuccessUrl("/");

        //
//        http.csrf().disable();

        // 开启退出登录功能
        http.logout().logoutSuccessUrl("/login");

        // 记住我
//        http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(3600);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 定制认证规则
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password(passwordEncoder().encode("123456")).roles("VIP1")
                .and()
                .withUser("lisi").password(passwordEncoder().encode("123456")).roles("VIP1", "VIP2")
                .and()
                .withUser("wangwu").password(passwordEncoder().encode("123456")).roles("VIP1", "VIP2", "VIP3")
                .and()
                .withUser("guest").password(passwordEncoder().encode("guest")).roles("VIP1", "VIP2", "VIP3");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
