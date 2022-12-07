package kh.study.team2.shop.config;

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
							, "/member/join"
							, "/member/login"
							, "/member/loginResult"
							, "/member/accessDenied"
//							, "/item/itemDetail"
							, "/item/searchResult"
							, "/member/searchId"
							, "/member/doSearchId"
							, "/member/searchPw"
							, "/member/sendEmail"
							, "/member/searchIdResult"
							, "/notice/list"
							, "/notice/noticeDetail"
							, "/**/**Ajax"
							, "/notice/qnaList").permitAll() //회원가입, 로그인, 게시글 목록, 게시글 상세보기, 아이디/비밀번호 찾기
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/favicon.ico", "/resources/**", "/error").permitAll()
				.anyRequest().authenticated() //로그인한 유저는 접근 허용
				//제일 마지막에 권한 해제
			.and()
				.formLogin()
				.loginPage("/member/login")
				.defaultSuccessUrl("/member/loginResult")
				.failureUrl("/member/loginResult")
				.loginProcessingUrl("/member/login")
				.usernameParameter("memberId") //여기에 입력한 이름으로 input의 아이디와 이름을 설정할 수 있음
				.passwordParameter("memberPw")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/member/accessDenied") //인증은 했지만 권한이 없어서 못 들어가는 페이지로 이동했을 때
			.and()
				.logout()
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/item/list");
				
				
		return security.build();
	}
	
	@Bean //비밀번호 암호화 자동으로 해 주는 객체 생성
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//security로 접근 권한을 설정하면 .js, .css 등 정적인 리소스에 접근도 권한을 체크
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}
	
	
	
	
	
	
	
	
	
}
