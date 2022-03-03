package com.mega.semilla.configuracionSeguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mega.semilla.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("usuarioServicio")
	public UsuarioServicio usuarioServicio;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioServicio).
		passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().and()
			.authorizeRequests()
				.antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
				.and().formLogin()
					.loginPage("/iniciar-sesion") //En el controlador de ->("/iniciar-sesion")
						.loginProcessingUrl("/logincheck")	 
						.usernameParameter("username")//mail      
						.passwordParameter("password")//contraseña
						.defaultSuccessUrl("/sesionactiva")
						.permitAll()
				.and().logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()
				.and().csrf()
					.disable();
	}
}
