package Com.EzenWeb.Service;

import Com.EzenWeb.Domain.Dto.BcategoryDto;
import Com.EzenWeb.Domain.Dto.BoardDto;
import Com.EzenWeb.Domain.entity.bcategory.BcategoryEntity;
import Com.EzenWeb.Domain.entity.bcategory.BcategoryRepository;
import Com.EzenWeb.Domain.entity.board.BoardEntity;
import Com.EzenWeb.Domain.entity.board.BoardRepository;
import Com.EzenWeb.Domain.entity.member.MemberEntity;
import Com.EzenWeb.Domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //컴포넌트
public class BoardService {//디비처리
    //==========================전역변수==================================//
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언
    @Autowired
    private BoardRepository boardRepository;    // 게시물 리포지토리 객체 선언
    @Autowired
    private MemberRepository memberRepository;  // 회원 리포지토리 객체 선언
    @Autowired
    private BcategoryRepository bcategoryRepository;    // 카테고리 리포지토리 객체 선언
    @Autowired
    private MemberService memberService;
    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    // 1. 메소드
            /*
                1. insert : boardRepository.save( 삽입할엔티티 )            BoardEntity entity
                2. select : boardRepository.findAll()                List<BoardEntity> elist
                3. select : boardRepository.findById( pk번호 )        Optional<BoardEntity> optional
                4. delete : boardRepository.delete( 삭제할엔티티 )
             */
    //==========================서비스==================================//

    @Transactional //1.게시물쓰기
    public boolean setboard(BoardDto boardDto){//1.게시물쓰기[첨부파일]
        // ---------- 로그인 회원 찾기 메소드 실행 --> 회원엔티티 검색 --------------  //
        MemberEntity memberEntity = memberService.getEntity();
        if( memberEntity == null ){ return false; }
        // ---------------------------- //
        // ------------ 선택한 카테고리 번호 --> 카테고리 엔티티 검색 --------------  //
        Optional<BcategoryEntity> optional = bcategoryRepository.findById( boardDto.getBcno() );
        if ( !optional.isPresent()) { return false;}
        BcategoryEntity bcategoryEntity = optional.get();
        // --------------------------  //
        BoardEntity boardEntity  = boardRepository.save( boardDto.toEntity() );  // 1. dto --> entity [ INSERT ] 저장된 entity 반환
        if( boardEntity.getBno() != 0 ){   // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공
            // 1. 회원 <---> 게시물 연관관계 대입
            boardEntity.setMemberEntity( memberEntity ); // ***!!!! 5. fk 대입
            memberEntity.getBoardEntities().add( boardEntity); // *** 양방향 [ pk필드에 fk 연결 ]
            // 2. 카테고리 <---> 게시물 연관관계 대입
            boardEntity.setBcategoryEntity( bcategoryEntity );
            bcategoryEntity.getBoardEntityList().add( boardEntity );
            return true;
        }
        else{ return false; } // 2. 0 이면 entity 생성 실패
    }
    @Transactional
    public List<BoardDto> boardlist(int bcno){ //2.게시물 목록조회[페이징,검색]
        List<BoardEntity> elist = null;
        if(bcno == 0){ //전체보기(0)이면 전체보기
            elist = boardRepository.findAll();
        }
        else{ //전체보기(0)가 아니면 카테고리별 보기
            BcategoryEntity bcentity = bcategoryRepository.findById(bcno).get();
            elist = bcentity.getBoardEntityList(); //해당 엔티티의 게시물 목록
        }
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
    public boolean setbcategory( BcategoryDto bcategoryDto){
        BcategoryEntity entity = bcategoryRepository.save(bcategoryDto.toEntity());
        if(entity.getBcno() != 0){return true;}
        else { return false;}
    }
    public List<BcategoryDto> bcategorylist(){
        List<BcategoryEntity> entityList = bcategoryRepository.findAll();
        List<BcategoryDto> dtolist = new ArrayList<>();//화살표함수 [람다식표현 js] (인수) => {실행코드}
        entityList.forEach(e -> dtolist.add(e.toDto()));
        return dtolist;
    }
}
// @Transactional  = 엔티티 수정(최소단위)

//클래스는 값이 없고 설계도 / 변수가 힙에 저장된 값을 불러옴(실제 메모리)

//클래스로 메소드를 가져올땐 스테틱만 가능

//insert = boardRepository.save(boardDto.toEntity());

//Optional = 포장   [optional.isPresent()]:isPresent()_null인지 확인하고 , optional_null까지 포장