package com.recipe.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소 인증 확인용
@Configuration
@Order(0)
public class SecurityConfig {

	@Autowired
	private SecurityOauth2Service oauth2Service;
	@Autowired
	private SecurityUserDetailsService suds;

	@Bean
	public RoleHierarchy roleHierarchy(){
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = "ROLE_ADMIN > ROLE_PARTNERSHIP > ROLE_CUSTOMER > ROLE_NOT_AUTH";
		roleHierarchy.setHierarchy(hierarchy);

		return roleHierarchy;
	}

	@Bean
	protected SecurityFilterChain filter(HttpSecurity security) throws Exception{

		security.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/recipe/**","/myPage/**","/board/**").hasAnyRole("CUSTOMER","ADMIN","PARTNERSHIP");
			auth.requestMatchers("/admin/**", "/mealkit/**").hasRole("ADMIN");
			auth.requestMatchers("/*","/source/**", "/member/**", "/system/**","/fragments/**","/common/**").permitAll();
		});
		security.csrf().disable();

		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/mainPage");
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/mainPage");
		security.userDetailsService(suds);
		security.oauth2Login().userInfoEndpoint().userService(oauth2Service);
		return security.build();

	}






	
	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}



