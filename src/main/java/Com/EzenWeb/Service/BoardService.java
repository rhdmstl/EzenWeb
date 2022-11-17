package Com.EzenWeb.Service;

import Com.EzenWeb.Domain.Dao.BoardDao;
import Com.EzenWeb.Domain.Dto.BoardDto;
import Com.EzenWeb.Domain.entity.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service //컴포넌트
public class BoardService {//디비처리
    //==========================전역변수==================================//
    @Autowired //없으면 빈에도 힙에도 없어서 nullpoint가 뜬다
    private BoardRepository boardRepository;
    //==========================서비스==================================//
    public boolean setboard(@RequestBody BoardDto boardDto){//1.게시물쓰기[첨부파일]

    }
    public List<BoardDto> boardlist(){

    }
    public BoardDto getboard(@RequestParam("bno") int bno){

    }
    public boolean deleteboard(@RequestParam("bno") int bno){

    }
    public boolean upboard(@RequestBody BoardDto boardDto){

    }
}
