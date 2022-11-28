package Com.EzenWeb.Service;


import Com.EzenWeb.Domain.Dto.BcategoryDto;
import Com.EzenWeb.Domain.Dto.BoardDto;
import Com.EzenWeb.Domain.Dto.GuestCategoryDto;
import Com.EzenWeb.Domain.Dto.GuestDto;
import Com.EzenWeb.Domain.entity.bcategory.BcategoryEntity;
import Com.EzenWeb.Domain.entity.bcategory.BcategoryRepository;
import Com.EzenWeb.Domain.entity.board.BoardEntity;
import Com.EzenWeb.Domain.entity.board.BoardRepository;
import Com.EzenWeb.Domain.entity.guestcategory.GuestCategoryEntity;
import Com.EzenWeb.Domain.entity.guestcategory.GuestCategoryRepository;
import Com.EzenWeb.Domain.entity.guset.GuestEntity;
import Com.EzenWeb.Domain.entity.guset.GuestRepository;
import Com.EzenWeb.Domain.entity.member.MemberEntity;
import Com.EzenWeb.Domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.*;

@Service //컴포넌트
public class BoardService {//디비처리
    //==========================전역변수==================================//
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언
    @Autowired
    HttpServletResponse response; //응답객체 선언
    @Autowired
    private MemberRepository memberRepository;  // 회원 리포지토리 객체 선언
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardRepository boardRepository;    // 게시물 리포지토리 객체 선언
    @Autowired
    private BcategoryRepository bcategoryRepository;   // 카테고리 리포지토리 객체 선언
    //////비회원게시판///////////////////////////////////////////////////////////////
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private GuestCategoryRepository guestCategoryRepository;
    ///////////////////////////////////////////////////////////////////////////////
    //*첨부파일 경로
    String path = "C:\\Users\\504\\Desktop\\springweb\\EzenWeb\\src\\main\\resources\\static";
    //==========================서비스==================================//
    //0.첨부파일 다운
    public void filedown(String filename){
        //uuid 제거
        String realfilename = "";
        String [] split = filename.split("_");//_기준으로 자르기 (사용자가 올린 파일명에 _가 있을수도있으니
        for(int i = 1; i < split.length; i++){ //uuid제외한 반복문 돌리기
            realfilename += split[i];           //뒷자리 문자열 추가
            if(split.length-1 == i ){ //마지막 인덱스일때
                realfilename += "_";
            }
        }
        String filepath = path + filename;  //1.경로찾기
        try {      //2.헤더구성        //다운로드 형식               다운로드에 표시할 파일명
            response.setHeader("Content-Dispoition", "application;filename=" + URLEncoder.encode(realfilename,"UTF-8"));
            File file = new File(filepath);
            //3.다운로드 할 바이트 읽어올 스트림 객체 선언
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[(int)file.length()];    //파일의 길이만큼 읽어와서 저장
            inputStream.read(bytes);    //읽어온 바이트 저장
            //출력
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(bytes);  //응답
            //버퍼 초기화 혹은 스트림 닫기
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (Exception e){ System.out.println("파일이름 표시 오류** : "+e); }
    }
    //첨부파일업로드
    @Transactional         //  boardDto : 쓰기,수정 대상     BoardEntity:원본
    public boolean fileupload(BoardDto boardDto , BoardEntity boardEntity) {
        if( boardDto.getBfile() != null ){   // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공
             //uuid
            String uuid = UUID.randomUUID().toString(); //난수생성
            String filename = uuid+"_"+boardDto.getBfile().getOriginalFilename();   // 2. 난수+파일명
            // * 첨부파일명 db 에 등록
            boardEntity.setBfile(filename); //읽어온 파일 저장

            // * 첨부파일 업로드 // 3. 저장할 경로 [ 전역변수 ]
            try {
                File uploadfile = new File(path+filename); //경로+파일명
                boardDto.getBfile().transferTo(uploadfile);
            }
            catch (Exception e){ System.out.println("**첨부파일업로드 오류 : "+e); }
            return true;
        }
        else{ return false; } // 2. 0 이면 entity 생성 실패
    }
   @Transactional
   public boolean setboard(BoardDto boardDto){
       // ---------- 로그인 회원 찾기 메소드 실행 --> 회원엔티티 검색 --------------  //
       MemberEntity memberEntity = memberService.getEntity();
       if(memberEntity == null){return false;}
       // ------------ 선택한 카테고리 번호 --> 카테고리 엔티티 검색 --------------  //
       Optional<BcategoryEntity> optional = bcategoryRepository.findById(boardDto.getBcno());
       if(!optional.isPresent()){return false;}
       BcategoryEntity bcategoryEntity = optional.get();

       BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());
       if(boardEntity.getBno() != 0){
           fileupload(boardDto,boardEntity); //업로드
           // 1. 회원 <---> 게시물 연관관계 대입
           boardEntity.setMemberEntity(memberEntity);
           memberEntity.getBoardEntities().add(boardEntity);
           // 2. 카테고리 <---> 게시물 연관관계 대입
           boardEntity.setBcategoryEntity(bcategoryEntity);
           bcategoryEntity.getBoardEntityList().add(boardEntity);
           return true;
       }
       else { return false; }
   }

    @Transactional   // bcno : 카테고리번호 , page : 현재 페이지 번호
    public List<BoardDto> boardlist(int bcno , int page , String key , String keyword){
        Page<BoardEntity> elist = null;

        // 1.Pageable 인터페이스 [ import 사용시 domain 패키지 ]
        // 2. PageRequest 구현 클래스
        // 1. PageRequest.of( 현재페이지번호 , 표시할레코드수 )
        Pageable pageable =  PageRequest.of( page-1 , 3 , Sort.by(Sort.Direction.DESC,"bno")); // 페이징 설정

        // 3. 검색여부 / 카테고리  판단
        if( key.equals("btitle") ){ // 검색필드가 제목이면
            elist = boardRepository.findbybtitle( bcno , keyword , pageable);
        }else if( key.equals("bcotent") ){ // 검색필드가 제목이면
            elist = boardRepository.findbybcontent( bcno , keyword , pageable);
        }else{ // 검색이 없으면 // 카테고리 출력
            if( bcno == 0  ) elist = boardRepository.findAll( pageable);
            else elist = boardRepository.findBybcno( bcno , pageable);
        }
        int btncount = 5;
        int startbtn = (page/btncount);
        int endbtn = startbtn + btncount-1;
        if(endbtn > elist.getTotalPages()){endbtn = elist.getTotalPages();}
        List<BoardDto> dlist = new ArrayList<>(); // 2. 컨트롤에게 전달할때 형변환[ entity->dto ] : 역할이 달라서
        for( BoardEntity entity : elist ){ // 3. 변환
            dlist.add( entity.toDto() );
        }
        dlist.get(0).setStartbtn(startbtn);
        dlist.get(0).setEndbtn(endbtn);

        return dlist;  // 4. 변환된 리스트 dist 반환
    }
    @Transactional
    public BoardDto getboard( int bno){ //3.게시물 개별조회
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional.isPresent()) { //Optional 안에 있는내용 확인
            BoardEntity entity = optional.get();
            return entity.toDto();
        }else {  return null;  }
    }
    @Transactional
    public boolean delboard(@RequestParam("bno") int bno){  //4.게시물삭제
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional.isPresent()) {
            BoardEntity boardEntity = optional.get(); //찾은 엔티티가져오기
            // 첨부파일 같이 삭제
            if( boardEntity.getBfile() != null ) {   // 기존 첨부파일 있을때
                File file = new File(path + boardEntity.getBfile()); // 기존 첨부파일 객체화
                if (file.exists()) {   file.delete(); }           // 존재하면 파일 삭제
            }
            boardRepository.delete(boardEntity); //찾은 엔티티 삭제
            return true; //전달
        }else {
            return false; //전달
        }
    }
    @Transactional
    public boolean upboard( BoardDto boardDto){ //5.게시물 수정[첨부파일 업로드]
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        if(optional.isPresent()){
            BoardEntity entity = optional.get();
            if(boardDto.getBfile() != null){ //1.수정할 첨부파일이 있을때 새로운 파일 업로드 및 저장
                if(entity.getBfile() != null){//기존첨부파일
                    File file = new File(path +  entity.getBfile()) ; //기존첨부파일 객체
                    if(file.exists()){ //존재하면
                        file.delete(); //파일 삭제
                    }
                    fileupload(boardDto,entity); //업로드
                }
            }
            entity.setBtitle( boardDto.getBtitle());
            entity.setBtitle( boardDto.getBcontent());
            boardRepository.save(entity);
            return true;
        }else { return false; }
    }
    @Transactional
    public boolean setbcategory(BcategoryDto bcategoryDto){
        BcategoryEntity entity = bcategoryRepository.save(bcategoryDto.toEntity());
        if(entity.getBcno() != 0){
            return true;
        }else {  return false; }
    }
    @Transactional
    public List<BcategoryDto> bcategorylist(){
        List<BcategoryEntity> entitiesList = bcategoryRepository.findAll();
        List<BcategoryDto> dtoList = new ArrayList<>();
        entitiesList.forEach(e -> dtoList.add(e.toDto()));
        return dtoList;
    }
    @Transactional
    public boolean setvisit(GuestDto guestDto){ //8.방명록 등록
        GuestEntity entity = guestRepository.save(guestDto.toEntity());
        // ------------ 선택한 카테고리 번호선택
        Optional<GuestCategoryEntity> optional = guestCategoryRepository.findById(guestDto.getBgcno());
        if(!optional.isPresent()){return false;}
        GuestCategoryEntity guestCategoryEntity = optional.get();

        if(entity.getBgno()!= 0){
            entity.setGuestCategoryEntity(guestCategoryEntity);
            guestCategoryEntity.getGuestEntity().add(entity);
            return true;
        }
        else { return false;}
    }
    @Transactional
    public List<GuestDto> guestlist(int bgcno){  //9.방명록 목록
        List<GuestDto> guestlist = new ArrayList<>();
        Optional<GuestCategoryEntity> optional = guestCategoryRepository.findById(bgcno);
        if(!optional.isPresent()){ return null;}
        if(bgcno == 4){
            System.out.println("test");
            List<GuestEntity> entitylist = guestRepository.findAll();
            for(GuestEntity entity : entitylist){
                guestlist.add(entity.toDto());
            }
        } else {
            GuestCategoryEntity guestCategoryEntity = optional.get();
            List<GuestEntity> list = guestCategoryEntity.getGuestEntity();
            for(GuestEntity guestEntity : list){
                guestlist.add(guestEntity.toDto());
            }
        }
        return guestlist;
    }
    @Transactional
   public boolean setvisitcategory( GuestCategoryDto guestCategoryDto){    //6.카테고리 등록
        GuestCategoryEntity entity = guestCategoryRepository.save(guestCategoryDto.toEntity());
        if(entity.getBgcno() != 0){return true;}
        return false;
    }
    @Transactional
    public List<GuestCategoryDto> visitcategorylist(){  //7.모든 카테고리 출력
        List<GuestCategoryEntity> entityList = guestCategoryRepository.findAll();
        List<GuestCategoryDto> gdtolist = new ArrayList<>();//화살표함수 [람다식표현 js] (인수) => {실행코드}
        entityList.forEach(e -> gdtolist.add(e.toDto()));
        return gdtolist;
    }
    @Transactional
    public boolean guestput(GuestDto guestDto){ //8.방명록 수정
        Optional<GuestEntity> optional = guestRepository.findById(guestDto.getBgno());
        if(optional.isPresent()){
            GuestEntity entity = optional.get();
            entity.setBgtitle(guestDto.getBgtitle());
            entity.setBgcontent(guestDto.getBgcontent());
            guestRepository.save(entity);
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    public boolean deleteguest(@RequestParam("bgno") int bgno){  //9.방명록 삭제
        Optional<GuestEntity> optional = guestRepository.findById(bgno);
        if(optional.isPresent()) {
            GuestEntity entity = optional.get(); //찾은 엔티티가져오기
            guestRepository.delete(entity); //찾은 엔티티 삭제
            return true; //전달
        }else {
            return false; //전달
        }
    }
}
// @Transactional  = 엔티티 수정(최소단위)

//클래스는 값이 없고 설계도 / 변수가 힙에 저장된 값을 불러옴(실제 메모리)

//클래스로 메소드를 가져올땐 스테틱만 가능

//insert = boardRepository.save(boardDto.toEntity());

//Optional = 포장   [optional.isPresent()]:isPresent()_null인지 확인하고 , optional_null까지 포장