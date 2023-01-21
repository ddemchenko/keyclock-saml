package com.amrut.prabhu.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class ResourceServerConfig {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http,
										 Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter) throws Exception {
		return http
			.authorizeRequests()
				.anyRequest().authenticated() // OR .access("authenticated AND hasRole('product_read')")
			.and()
				.oauth2ResourceServer()
					.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)).and().build();
	}

	@Bean
	public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter(RealmRoleConverter realmRoleConverter) {
		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(realmRoleConverter);
		return jwtConverter;
	}
}

