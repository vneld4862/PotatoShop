package kh.study.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.csrf().disable()
				.authorizeRequests()
				.antMatchers("/item/list"
						,"/item/itemDetail"
						,"/member/join"
						,"/member/login").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/member/login")
				.defaultSuccessUrl("/member/loginResult")
				.failureUrl("/member/loginResult")
				.loginProcessingUrl("/member/login") //실제 로그인을 진행할 요청 정보
				.usernameParameter("memberId")
				.passwordParameter("memberPw")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/member/accessDenied")
			.and()
				.logout()
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/item/list");
				
		return security.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	return (web) -> web.ignoring().antMatchers("/js/**",  "/css/**","/images/**");
    }
	
}
