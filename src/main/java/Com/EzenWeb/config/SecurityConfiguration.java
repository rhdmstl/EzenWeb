package Com.EzenWeb.config;

import Com.EzenWeb.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration ///설정 컴포넌트 주입//22년 3월에 사라져서 밑줄그어짐
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MemberService memberService;

    @Override //인증(로그인)관련 메소드 재정의
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //
        auth.userDetailsService(memberService).passwordEncoder((new BCryptPasswordEncoder()) );
        // memberService에서 userDetailsService구현했다
        // AuthenticationManagerBuilder : 토큰 전달
        // passwordEncoder((new BCryptPasswordEncoder())) : 비밀번호 검증 (빌드매니저가 DTO 지나서 검사함)
    }

    @Override   // 재정의 [ 상속받은 클래스로부터 메소드 재구현 ]
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()                                                                    // 로그인 페이지 보안 설정
                    .loginPage("/member/login")                                                 // 아이디와 비밀번호를 입력받을 URL [ 로그인 페이지 ]
                    .loginProcessingUrl("/member/getmember")                                    // 로그인을 처리할 URL
                    .defaultSuccessUrl("/")                                                     // 로그인 성공했을때 이동할 URL
                    .failureUrl("/member/login")                              // 로그인 실패시 이동할 URL
                    .usernameParameter("memail")                                                // 아이디 변수명
                    .passwordParameter("mpassword")                                             // 비밀번호 변수명
                .and()                                                                          // 기능구분
                    .logout()                                                                   // 로그아웃 보안설정
                    .logoutRequestMatcher( new AntPathRequestMatcher("/member/logout"))  // 로그아웃 처리 URL 정의
                    .logoutSuccessUrl("/")                                                      // 로그아웃 성공시 이동할 URL
                    .invalidateHttpSession(true)                                                // 세션 초기화 [ principal ]
                .and()                                                                          // 기능구분
                .csrf()                                                                         // 요청 위조 방지
                    .ignoringAntMatchers("/member/getmember")                        // 해당 URL 요청 방지 해지
                    .ignoringAntMatchers("/member/setmember")                        // 회원가입 post 사용
                .and()
                    .oauth2Login()                                                               //소셜로그인 보안설정
                    .userInfoEndpoint()                                                          //회원정보 받는곳
                    .userService(memberService);                                                 //해당 서비스 구현
        //  super.configure(http); // 모든 HTTP 보안설정

    }
}

/*
     시큐리티 사용방법
    1. 그레이들 의존성 추가
           1. implementation 'org.springframework.boot:spring-boot-starter-security' // 스프링 시큐리티 [인증/인가]
    2.  시큐리티 커스텀 [ 기본 설정값 변경 ]
            @Configuration : 컴포넌트 시리즈 [ @Controller , @Service , @Entity , @Configuration 등 ]
           1. extends WebSecurityConfigurerAdapter 클래스 상속받아서 커스텀 클래스 선언
                1. configure(HttpSecurity http) : http 보안 메소드
                2.

    시큐리티 기본값
            1. 해당 프로젝트의 모든 HTTP URL이 잠긴다.
                    -> 커스텀 : http 권한 없애기
                             @Override   // 재정의 [ 상속받은 클래스로부터 메소드 재구현 ]
                                 protected void configure(HttpSecurity http) throws Exception {

                                 }
      //  super.configure(http); // 모든 HTTP 보안설정
            2. 기본 login html 제공
            3. login controller 제공
            4. login service 제공
            ----------------> 커스텀 작업 -> SucurityConfiguration : 시큐리티 설정 클래스
                        WebSecurityConfigurerAdapter : 웹시큐리티 설정 클래스
                            설정 종류
                                1. URL 권한
**/
