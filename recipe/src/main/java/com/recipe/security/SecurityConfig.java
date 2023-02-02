package com.recipe.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {



	@Autowired
	private SecurityUserDetailsService suds;
	
	@Bean
	protected SecurityFilterChain filter(HttpSecurity security) throws Exception{
		security.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/**", "/member/**", "/system/**","/flagments/**","/common/**").permitAll();
			auth.requestMatchers("/mypage/**","/board/**").authenticated();
			auth.requestMatchers("/admin/**").hasRole("ADMIN");
		});
		security.csrf().disable();
		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/mainPage", true);
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/mainPage");
		security.userDetailsService(suds);
		
		return security.build();
	}



	
	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
