package telran.spring.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.authorizeHttpRequests(requests -> 
			requests.anyRequest().authenticated())
		.httpBasic();
		
		return http.build();
	}
}
