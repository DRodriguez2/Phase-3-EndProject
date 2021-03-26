package com.HCL.Phase3.TaskManager.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.HCL.Phase3.TaskManager.User.User;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.antMatchers("/").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/home/**").hasRole("USER")
			.and()
				.formLogin()
				.loginPage("/loginPage")
				.failureUrl("/loginPage?error=true")
				.permitAll()
				.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
			            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			            User user = userDetails.getUser();
			            
			            request.getSession().setAttribute("user", user);
			            request.getSession().setAttribute("tasks", user.getTasks());
			            super.onAuthenticationSuccess(request, response, authentication);
					}
				})
			.and()
				.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/denied");
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/**");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
