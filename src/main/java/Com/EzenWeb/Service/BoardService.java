package Com.EzenWeb.Service;

import Com.EzenWeb.Domain.Dao.BoardDao;
import Com.EzenWeb.Domain.Dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service //컴포넌트
public class BoardService {
    //자동 메모리생성(힙) @Autowired
   // BoardDao boardDao;
    //1.게시물 등록 서비스
    public boolean setboard( BoardDto boardDto){
        return new BoardDao().setboard(boardDto);
    }
    public ArrayList<BoardDto> getboards(){
        return new BoardDao().getboards();
    }
}
