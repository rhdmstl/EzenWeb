package Com.EzenWeb.config;

import Com.EzenWeb.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration ///설정 컴포넌트 주입//22년 3월에 사라져서 밑줄그어짐
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MemberService memberService;
//훈이가 해줌
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        ///super.configure(http);
       http
               .formLogin()                                           //로그인 페이지 보안설정
               .loginPage("/member/login")                           //아이디와 비밀번호를 입력받을 URL
               .loginProcessingUrl("/member/getmember")             //로그인을 처리할 URL
               .defaultSuccessUrl("/")                             //로그인 성공 시 이동할 URL
               .failureUrl("/member/login")     //로그인실패 시 이동
               .usernameParameter("memail")                      //아이디 변수명
               .passwordParameter("mpassword")                     //비밀번호 변수명
               .and()                                    //기능구분
               .csrf()                                      //요청위조 방지
               .ignoringAntMatchers("/member/getmember")    //로그인 post사용 해당 URL 요청방지 해지
               .ignoringAntMatchers("/member/getmember"); //회원가입 post사용 해당 URL 요청방지 해지

    }
}
/*
    시큐리티 사용법
        1.그레이들 의존성 추가
            implementation 'org.springframework.boot:spring-boot-starter-security' //인증 인가
        2.@Configuration 설정 컴포넌트 주입 (시큐리티 커스텀[기본 설정값 변경])
            1. extends WebSecurityConfigurerAdapter 상속받아서 커스텀 클래스 선언
            2. configure(HttpSecurity http) http 보안메소드
    시큐리티 기본값
        1.해당 프로젝트의 모든 URL이 잠긴다
        2.login html 제공 -> 커스텀 로그인 페이지 변경가능
        3.login controller 제공
        4.login service 제공

            설정종류
                1.URL 설정권한
*/
