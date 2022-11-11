package Com.EzenWeb.controller;

import Com.EzenWeb.Domain.Dto.BoardDto;
import Com.EzenWeb.Service.BoardService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController //연동됨 MVC지향
@RequestMapping("/board")
public class BoardController {
    //1.게시물 목록 페이지 열기
    @GetMapping("/list") //URL정의
    public Resource list() {
        return new ClassPathResource("templates/board/list.html");
    }
    //2.글쓰기 페이지 열기
    @GetMapping("/write")
    public Resource write() {
        return new ClassPathResource("templates/board/write.html");
    }
    //--------기능처리---------///
    //1.게시물쓰기 [첨부파일]
    @PostMapping("/setboard")
    public boolean setboard(@RequestBody BoardDto boardDto) {
        //1.Dto내용확인
        System.out.println(boardDto.toString());
        //2.서비스 [비지니스 로직] 이동
        boolean result = new BoardService().setboard(boardDto);
        //3.반환
        return true;
    }
    //2.게시물 목록보기 처리[페이징 , 검색]
    @GetMapping("/getboards")
    @ResponseBody
    public ArrayList<BoardDto> getboards() {
        //1.서비스 호출
        ArrayList<BoardDto> list = new BoardService().getboards();
        //2.반환
        return list;
    }

    //3.개별조회
  //  @GetMapping("/getboard")

    //4.게시물 수정처리
  //  @PutMapping("/update")

    //5.게시물 삭제처리
  //  @DeleteMapping("/deleteboard")
}
