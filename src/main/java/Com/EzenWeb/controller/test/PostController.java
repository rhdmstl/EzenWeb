package Com.EzenWeb.controller.test;

import Com.EzenWeb.Domain.Dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //스프링이 해당 클래스의 RestController임을 빈에 등록
@RequestMapping("/api/v1/post-api") //공통 URL
public class PostController {
    //68
    @RequestMapping(value = "/domain",method = RequestMethod.POST)
    public String postExample(){
        return "Hello Post API";
    }
    //69
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String,String> postData){
        return postData.toString();
    }
    @PostMapping("/member2")
    public String postMember2(@RequestBody MemberDto dto){
        return dto.toString();
    }
}
