package Com.EzenWeb.controller;

import Com.EzenWeb.Domain.Dto.BcategoryDto;
import Com.EzenWeb.Domain.Dto.BoardDto;
import Com.EzenWeb.Domain.Dto.GuestCategoryDto;
import Com.EzenWeb.Domain.Dto.GuestDto;
import Com.EzenWeb.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //연동됨 MVC지향
@RequestMapping("/board") //공통 url[localhost:8080/board/...]
public class BoardController { //요청/응답만 한다
    //==========================전역변수==================================//
    @Autowired //스프링 컨테이너 빈[메모리] 할당 객체 생성
    private BoardService boardService; //서비스

    //============================페이지 로드[HTML반환]====================//
    //1.게시판목록 열기
    @GetMapping("/list") //도메인 주소는 무조건 get
    public Resource getlist() {
        return new ClassPathResource("/templates/board/list.html");
    }

    //2.게시물쓰기 열기
    @GetMapping("/write")
    public Resource setwrite() {
        return new ClassPathResource("/templates/board/write.html");
    }

    //3.게시물조회 열기
    @GetMapping("/view")
    public Resource setview() {
        return new ClassPathResource("/templates/board/view.html");
    }

    //4.게시물수정 열기
    @GetMapping("/update")
    public Resource setupdate() {
        return new ClassPathResource("/templates/board/update.html");
    }

    @GetMapping("/guest")
    public Resource guest() {
        return new ClassPathResource("/templates/board/guest.html");
    }

    //==========================모델요청과 응답==================================//
    @PostMapping("/setboard") //1.게시물쓰기[첨부파일]
    public boolean setboard(@RequestBody BoardDto boardDto) {
        return boardService.setboard(boardDto);
    }

    @GetMapping("/boardlist")//2.게시물 목록조회[페이징,검색]
    public List<BoardDto> boardlist(@RequestParam("bcno") int bcno) {
        return boardService.boardlist(bcno);
    }

    @GetMapping("/getboard")//3.게시물 개별조회
    public BoardDto getboard(@RequestParam("bno") int bno) {
        return boardService.getboard(bno);
    }

    @DeleteMapping("/delboard") //4.게시물삭제
    public boolean deleteboard(@RequestParam("bno") int bno) {
        return boardService.deleteboard(bno);
    }

    @PutMapping("/upboard")//5.게시물 수정[첨부파일]
    public boolean upboard(@RequestBody BoardDto boardDto) {
        return boardService.upboard(boardDto);
    }

    @PostMapping("/setbcategory")//6.카테고리 등록
    public boolean setbcategory(@RequestBody BcategoryDto bcategoryDto) {
        return boardService.setbcategory(bcategoryDto);
    }

    @GetMapping("/bcategorylist") //7.모든 카테고리 출력
    public List<BcategoryDto> Bcategorylist() {
        return boardService.bcategorylist();
    }

    @PostMapping("/setvisit") //8.방명록 등록
    public boolean setvisit(@RequestBody GuestDto guestDto) {
        return boardService.setvisit(guestDto);
    }

    @GetMapping("/guestlist") //9.방명록 목록
    public List<GuestDto> guestlist() {
        return boardService.guestlist();
    }

    @PostMapping("/setvisitcategory")//6.카테고리 등록
    public boolean setvisitcategory(@RequestBody GuestCategoryDto guestCategoryDto) {
        return boardService.setvisitcategory(guestCategoryDto);
    }

    @GetMapping("/visitcategorylist") //7.모든 카테고리 출력
    public List<BcategoryDto> visitcategorylist() {
        return boardService.bcategorylist();
    }

}
//@RequestBody = 한번에 받기
//@RequestParam = 하나씩 가져오기
//@PathVariable = {변수} 괄호안에 괄호 그레이스를 열어 URI에 사용될 변수 명을 입력
//HTTP = 데이터를 주고받기 위해 정의