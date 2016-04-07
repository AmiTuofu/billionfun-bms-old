package com.billionfun.bms.product.mall.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.billionfun.bms.product.mall.service.impl.SysUserServiceImpl;

/*@EnableWebSecurity*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private SysUserServiceImpl userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/login.html").permitAll()
		.anyRequest().authenticated()
		.antMatchers("/*.html").hasRole("USER")
		.and().formLogin()
		.loginPage("/login.html").permitAll()
		.defaultSuccessUrl("/index.html")
		.and()
		.logout()
		.logoutSuccessUrl("/login.html")
		.permitAll()
		.invalidateHttpSession(true)
		.and().csrf().disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//		auth
//        .inMemoryAuthentication()
//            .withUser("user").password("password").roles("USER");
//		auth.userDetailsService(userService);
	}
}
