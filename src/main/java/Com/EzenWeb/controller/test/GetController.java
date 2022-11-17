package Com.EzenWeb.controller.test;

import Com.EzenWeb.Domain.Dto.MemberDto;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//56p

@RestController
@RequestMapping("/api/v1/get-api") //요청 url정의하기
public class GetController {
    //57p @GetMapping("/hello")로 대체 가능
    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String getHello(){
        return "메소드 들어옴";
    }
    //58p
    @GetMapping("/name")
    public String getName(){
        return "Flatrue";
    }
    //59p
    @GetMapping("/variable1/{variable}") //http://localhost:8080/api/v1/get-api/variable1/변수값
    public String getVariable1(@PathVariable String variable){
        return variable;
    }
    //60p
    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable(value = "variable")String test){
        return test;
    }
    //@PathVariable(하나) vs @RequestParam(여러개)
    // 변수보낼때 '/' vs '?' 의 차이

    //4-2[비교]
    @GetMapping("/variable3")
    public String getVariable3(@RequestParam String variable){
        return variable;
    }
    //61p
    @GetMapping("/requst1") //?변수명=값 & 변수명2=값2(띄어쓰기 안함)[/requst1?name=짱이다&email=체리]
    public String getRequstParam1(@RequestParam String name , @RequestParam String email , @RequestParam String organization){
        return name + " "+email+" " +organization;
    }
    //62p

    @GetMapping("/requst2")
    public String getRequstParam2(@RequestParam Map< String , String> param){
        return param.toString();
    }
    @GetMapping("/requst3")
    public String getRequstParam3(MemberDto dto){
        return dto.toString();
    }
}
/* java 컬렉션 프레임워크
*   1.list : 인덱스[중복가능], set : 인덱스[중복불가] , map : 인덱스[키 : 값] 엔트리 사용
*
* */