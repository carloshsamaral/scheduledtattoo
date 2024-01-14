package br.com.adaconsultoria.config;

import org.springframework.context.annotation.Configuration;

@SuppressWarnings("deprecation")
@Configuration

//public class JWTConfiguration extends WebSecurityConfigurerAdapter {
	public class JWTConfiguration  {	
}
/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().addFilterAfter(new JwtSecurity(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers(HttpMethod.POST, "/api/criar-conta").permitAll()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.antMatchers(HttpMethod.POST, "/api/password-recover").permitAll()
				.antMatchers(HttpMethod.POST, "/api/register-user").permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll().anyRequest().authenticated();
	}

	// configuração para liberar a documentação do SWAGGER
	private static final String[] SWAGGER = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**",
			"/swagger-ui/**" };

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(SWAGGER);
	}*/
