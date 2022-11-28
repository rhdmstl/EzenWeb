package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration ///설정 컴포넌트 주입
                                            //22년 3월에 사라져서 밑줄그어짐
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        //super.configure(http);    http를 열고 닫는 보안
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
