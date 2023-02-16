package com.recipe.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@Order(0)
public class SecurityConfig {

//	@Autowired
//	private SecurityOauth2Service oauth2Service;
	@Autowired
	private SecurityUserDetailsService suds;

	@Bean
	protected SecurityFilterChain filter(HttpSecurity security) throws Exception{
		security.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/*","/source/**", "/member/**", "/system/**","/fragments/**","/common/**").permitAll();
			auth.requestMatchers("/recipe/**","/myPage/**","/board/**").authenticated();
			auth.requestMatchers("/admin/**", "/mealkit/**").hasRole("ADMIN");
		});

		security.csrf().disable();
		// OAuth2 를 이용한 구글 로그인 방식
/*
		security.headers().frameOptions().disable();
		security.formLogin().disable()
				.httpBasic().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.oauth2Login().userInfoEndpoint().userService(oauth2Service);
		security.logout().logoutSuccessUrl("/oauth2Suc");
		return security.build();
*/
		// 세션을 이용한 기존의 로그인 방식

		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/mainPage", true);
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/mainPage");
		security.userDetailsService(suds);
		return security.build();

	}

	@Bean
	public RoleHierarchy roleHierarchy1(){
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = "ROLE_ADMIN > ROLE_PARTNERSHIP > ROLE_CUSTOMER";
		roleHierarchy.setHierarchy(hierarchy);

		return roleHierarchy;
	}

	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler1(){
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy1());
		return expressionHandler;
	}


	
	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
/*
@EnableWebSecurity
@Configuration
@Order(1)
class SecurityConfig2 {

	@Autowired
	private SecurityUserDetailsService suds;

	@Bean
	protected SecurityFilterChain filter2(HttpSecurity security) throws Exception{
		security.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/*","/source/**", "/member/**", "/system/**","/fragments/**","/common/**").permitAll();
			auth.requestMatchers("/recipe/**","/myPage/**","/board/**").authenticated();
			auth.requestMatchers("/admin/**", "/mealkit/**").hasRole("ADMIN");
		});
	security.csrf().disable();
		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		security.formLogin().disable()
				.httpBasic().disable()
				.oauth2Login().userInfoEndpoint().userService(oauth2Service);

		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/mainPage", true);
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/mainPage");
		security.userDetailsService(suds);
		return security.build();
	}


	public RoleHierarchy roleHierarchy(){
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = "ROLE_ADMIN > ROLE_PARTNERSHIP > ROLE_CUSTOMER";
		roleHierarchy.setHierarchy(hierarchy);

		return roleHierarchy;
	}

	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy());
		return expressionHandler;
	}




	@Bean
	public PasswordEncoder encoder2() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
*/