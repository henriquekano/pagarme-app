package br.com.pagarme.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.pagarme.application.service.impl.UserManagerService;

@Configuration
@EnableWebSecurity
public  class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/register").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	// Usuario default
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserManagerService userService) throws Exception {
		//seta o cara que faz a transição (usuário do spring) <-> (usuário da aplicação)
		auth.userDetailsService(userService);
 		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
}