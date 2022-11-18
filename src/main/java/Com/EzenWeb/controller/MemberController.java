package Com.EzenWeb.controller;

import Com.EzenWeb.Domain.Dto.MemberDto;
import Com.EzenWeb.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController //해당클래스가 RestController명시
@RequestMapping("/member")
public class MemberController {
    //매핑주소,함수명 중복 불가
    // --------------------------------- 전역 객체  --------------------------------------- //
    @Autowired
    private MemberService memberService; //리포지토리 객체

    // --------------------------------- HTML 반환 매핑 ---------------------------------- //
    @GetMapping("/signup")
    public Resource getsignup() {
        return new ClassPathResource("templates/member/signup.html");
    }

    @GetMapping("/login")
    public Resource getlogin() {
        return new ClassPathResource("templates/member/login.html");
    }

    @GetMapping("/findpw")
    public Resource findpw() {
        return new ClassPathResource("templates/member/findpw.html");
    }

    @GetMapping("/delete")
    public Resource delete() {
        return new ClassPathResource("templates/member/delete.html");
    }

    @GetMapping("/update")
    public Resource update() {
        return new ClassPathResource("templates/member/update.html");
    }


    // --------------------------------- 서비스/기능 매핑 ------------------------------------- //
    @PostMapping("/setmember") //회원가입
    public int setmember(@RequestBody MemberDto memberDto) {
        int result = memberService.setmember(memberDto);
        return result;
    }

    @PostMapping("/getmember")//로그인
    public int getmember(@RequestBody MemberDto memberDto) {
        int result = memberService.getmember(memberDto);
        return result;
    }

    @GetMapping("/getpassword")
    public String getpassword(@RequestParam("memail") String memail) {
        String result = memberService.getpassword(memail);
        return result;
    }

    @DeleteMapping("/setdelete")
    public int setdelete(@RequestParam("mpassword") String mpassword) {
        int result = memberService.setdelete(mpassword);
        return result;
    }

    @PutMapping("/setupdate")
    public int setupdate(@RequestParam("mpassword") String mpassword) {
        int result = memberService.setupdate(mpassword);
        return result;
    }

    @GetMapping("/getloginMno")
    public int getloginMno() {
        int result = memberService.getloginMno();
        return result;
    }

    @GetMapping("/logoutMno")
    public int logoutMno() {
        int result = memberService.logoutMno();
        return result;
    }

    @GetMapping("/list") // 8. 회원 목록
    @ResponseBody
    public List<MemberDto> list() {
        List<MemberDto> list = memberService.list();
        System.out.println("확인:" + list);
        return list;
    }
    @GetMapping("/getauth")
    public String getauth(@RequestParam("toemail")String toemail) {
        //return memberService.getauth(toemail);
        return "1234";
    }
}
