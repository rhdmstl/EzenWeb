package Com.EzenWeb.controller;

import Com.EzenWeb.Domain.Dto.MemberDto;
import Com.EzenWeb.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") //요청포트 변경 어노테이션
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
    @PostMapping("/setmember") // 1. 회원가입 기능
    public int setmember( @RequestBody MemberDto memberDto  ){
        int result = memberService.setmember( memberDto ); // 1. 서비스[ 비지니스 로직 ] 호출
        return result;  // 2. 반환
    }
    /*  @PostMapping("/getmember") // 2. 로그인 기능 [ 시큐리티 사용시 필요없음 ]
      public int getmember( @RequestBody MemberDto memberDto ){
          int result = memberService.getmember( memberDto );
          return result;
      }*/
    @GetMapping("/getpassword") // 3. 이메일찾기
    public String getpassword( @RequestParam("memail") String memail ){
        String result = memberService.getpassword( memail );
        return result;
    }

    @DeleteMapping("/setdelete") //  4. 탈퇴
    public int setdelete( @RequestParam("mpassword") String mpassword ){
        // 1. 서비스처리
        int result = memberService.setdelete( mpassword );
        // 2. 서비스결과 반환
        return result;
    }

    @PutMapping("setupdate") // 5. 비밀번호 수정
    public int setupdate( @RequestParam("mpassword") String mpassword){
        int result = memberService.setupdate(mpassword);
        return result;

    }

    @GetMapping("/getloginMno")  // 6.
    public  String getloginMno(){
        String result = memberService.getloginMno();
        return result;

    }

/*
    @GetMapping("/logout") // 7. 로그아웃
    public  void logout(){
         memberService.logout();
    }
*/

    @GetMapping("/list") // 8. 회원목록
    @ResponseBody
    public List<MemberDto> list(){
        List<MemberDto> list = memberService.list();
        System.out.println("확인 : "+list);
        return list;
    }

    @GetMapping("/getauth")
    public  String getauth(@RequestParam("toemail") String toemail){
        //  return  memberService.getauth( "dlwhdgns47@naver.com" );
        return  "1234";
    }

}
