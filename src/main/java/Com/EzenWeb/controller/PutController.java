package Com.EzenWeb.controller;

import Com.EzenWeb.Domain.Dto.MemberDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    //70p
    @RequestMapping(value="member")
    public String putMember(@RequestBody Map<String, String> pudata) {
        return pudata.toString();
    }

    //71p반환타입 : 문자열[String]
    @PutMapping("member1")
    public String putMemberDto(@RequestBody MemberDto dto) {
        return dto.toString();
    }
    //72 반환타입 : 객체 [MemberDto]
    @PutMapping("member2")
    public MemberDto putMemberDto2(@RequestBody MemberDto dto) {
        return dto;
    }
}
