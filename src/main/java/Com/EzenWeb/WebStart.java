package Com.EzenWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication //스프링 기본설정값(1.컴포넌트 스캔 2.설정파일 실행(properties)
@EnableJpaAuditing //jpa 감시[ 생성 , 변경]
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class); //현재클래스명.클래스
    }
}
/*
    1.extend : 상속[메모리(설계도)를 물려받음]
    2.@(어노테이션) (여러개 상속 가능)
        -내장 : @override 상속메소드 재정의할때
        -메타 : 빌드[실행] 할때 자동코드실행


    1. @SpringBootApplication : 컴포넌트 스캔
        컴포넌트 사용하는 클래스들을 스캔 빈[스프링 메모리] 등록
                1. @Controller
                2. @RestContrller
*/