package Com.EzenWeb.Service;

import Com.EzenWeb.Domain.Dao.BoardDao;
import Com.EzenWeb.Domain.Dto.BoardDto;
import Com.EzenWeb.Domain.entity.BoardEntity;
import Com.EzenWeb.Domain.entity.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //컴포넌트
public class BoardService {//디비처리
    //==========================전역변수==================================//
    @Autowired //없으면 빈에도 힙에도 없어서 nullpoint가 뜬다
    private BoardRepository boardRepository;
    //==========================서비스==================================//
    @Transactional
    public boolean setboard(@RequestBody BoardDto boardDto){//1.게시물쓰기[첨부파일]
        BoardEntity entity = boardRepository.save(boardDto.toEntity());
        if(entity.getBno() != 0){ //생성된 엔티티의 게시물 번호가 0아니면 성공
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    public List<BoardDto> boardlist(){ //2.게시물 목록조회[페이징,검색]
        List<BoardEntity> elist = boardRepository.findAll();
        //컨트롤한테 전달하기위해 entity -> dto 형변환하기
        List<BoardDto> dlist = new ArrayList<>();
        //변환
        for(BoardEntity entity : elist){
            dlist.add(entity.toDto());
        }
        return dlist; //반환
    }
    @Transactional
    public BoardDto getboard( int bno){ //3.게시물 개별조회
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional.isPresent()) { //Optional 안에 있는내용 확인
            BoardEntity entity = optional.get();
            return entity.toDto();
        }else {
            return null;
        }
    }
    @Transactional
    public boolean deleteboard(@RequestParam("bno") int bno){  //4.게시물삭제
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional.isPresent()) {
            BoardEntity entity = optional.get(); //찾은 엔티티가져오기
            boardRepository.delete(entity); //찾은 엔티티 삭제
            return true; //전달
        }else {
            return false; //전달
        }
    }
    @Transactional
    public boolean upboard(@RequestBody BoardDto boardDto){ //5.게시물 수정[첨부파일]
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        if(optional.isPresent()){
            BoardEntity entity = optional.get();
            entity.setBtitle( boardDto.getBtitle());
            entity.setBtitle( boardDto.getBcontent());
            entity.setBtitle( boardDto.getBfile());
            boardRepository.save(entity);
            return true;
        }else {
            return false;

        }
    }
}
// @Transactional  = 엔티티 수정(최소단위)

//클래스는 값이 없고 설계도 / 변수가 힙에 저장된 값을 불러옴(실제 메모리)

//클래스로 메소드를 가져올땐 스테틱만 가능

//insert = boardRepository.save(boardDto.toEntity());

//Optional = 포장   [optional.isPresent()]:isPresent()_null인지 확인하고 , optional_null까지 포장