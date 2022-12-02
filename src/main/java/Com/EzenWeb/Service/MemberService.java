package Com.EzenWeb.Service;

import Com.EzenWeb.Domain.Dto.MemberDto;
import Com.EzenWeb.Domain.Dto.OathDto;
import Com.EzenWeb.Domain.entity.member.MemberEntity;
import Com.EzenWeb.Domain.entity.member.MemberRepository;
import Com.EzenWeb.config.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service //해당클래스가 Service명시 ///implements UserDetailsService 시큐리티를 통해 임포트
public class MemberService implements UserDetailsService , OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //1.인증[로그인] 정보요청
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        //2.oath2 클라이언트 식별 [네이버 vs 카카오 vs 구글]
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //3.회원정보 [ JSON ]
        String oath2UserInfo = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        System.out.println("회원정보 확인"+oath2UserInfo);

        //4.dto처리
        OathDto oathDto = OathDto.of(registrationId,oath2UserInfo,oAuth2User.getAttributes());

        //5.DB처리
        ///1.이메일로 엔티티 확인 [기존 회원 확인]
        Optional<MemberEntity> optional = memberRepository.findByMemail(oathDto.getMemail());
        MemberEntity memberEntity = null;
        if(optional.isPresent()) {   //Optional : null오류 방지를 위한 예외처리 방지
            if (optional.get().getMrol().equals(registrationId)) {
                memberEntity = optional.get();
            } else {  //신규 회원일때
                memberEntity = memberRepository.save(oathDto.toEntity());
            }
        }
        System.out.println("1번"+memberEntity);

        //권한
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(memberEntity.getMrol()));

        //6.반환[세션]
        MemberDto memberDto = new MemberDto();
        memberDto.setMemail(memberEntity.getMemail());
        memberDto.setAuthorities(authorities);
        memberDto.setAttributes(oAuth2User.getAttributes());
        return memberDto;
    }
    // ------------------------------- 전역 객체 -------------------------------//
    @Autowired
    private MemberRepository memberRepository;
    @Autowired //스프링 컨태이너 메모리위임
    private HttpServletRequest request; //용청객체(매핑을 안쓸때 쓰면됌)
    //mail sender 객체
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    // -------------------------------- 서비스 메소드 --------------------------//
    // * 로그인된 엔티티 호출
    public MemberEntity getEntity(){
        // 1. 로그인 정보 확인[ 세션 = loginMno ]
        Object object = request.getSession().getAttribute("loginMno" );
        if( object == null ){return null;}
        // 2. 로그인된 회원정보 호출
        int mno = (Integer)object;
        // 3. 회원정보 --> 회원정보 호출
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if(!optional.isPresent()){return null;}
        // 4. 로그인된 회원의 엔티티
        return optional.get();
    }

    // 1. [ 일반 ] 회원가입
    @Transactional
    public int setmember(MemberDto memberDto) {

        // 암호화 : 해시함수 사용하는 암호화 기법중 하나 [ BCrypt ]
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setMpassword( passwordEncoder.encode((memberDto.getPassword() ) ) );

        // 1. DAO 처리 [ dto --> entity  insert]
        MemberEntity entity = memberRepository.save(memberDto.toEntity());
        // memberRepository.save( 엔티티 객체 ) : 해당 엔티티 객체가 insert 생성된 엔티티객체 반환
        //회원 등급 넣어주기
        entity.setMrol("user");
        // 2. 결과 반환 [ 생성된 엔티티의 pk값 반환 ]
        return entity.getMno();
    }

    // 2. 로그인 [ 시큐리티 사용시 필요없음 ]
    /*@Transactional
    public int getmember(MemberDto memberDto) {
        // 1. dao 처리 [ select ]
        // 2. 모든 레코드=entity 호출 [ select * from member ]
        List<MemberEntity> entityList = memberRepository.findAll();
        // 2. 입력받은 데이터와 일치값 찾기
        for (MemberEntity entity : entityList) {
            if (entity.getMemail().equals(memberDto.getMemail() ) ) { // 엔티티=레코드 의 이메일과 입력받은 이메일
                if (entity.getMpassword().equals(memberDto.getMpassword())) { // 엔티티=레코드 의 비밀번호와 입력받은 비밀번호
                   // 세션 부여 [ 로그인 성공시 'loginMno 이름으로 회원번호 세션에 저장']
                    request.getSession().setAttribute("loginMno" ,  entity.getMno());
                    return 1; // 로그인 성공했다.
                } else {
                }
                return 2; // 패스워드 틀림
            }
        }
        return 0; // 로그인 실패했다. 아이디가 틀림
    }*/

    // 2. [ 시큐리티 사용시 ] 로그인 인증 메소드 재정의 [ Override ]
    @Override
    public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException {

        // 1. 입력받은 아이디 [ memail ]
        MemberEntity memberEntity =
                memberRepository
                        .findByMemail(memail)
                        .orElseThrow( ()-> new UsernameNotFoundException("사용자가 존재하지 않습니다."));
        // .orElseThrow : 검색결과가 없으면 화살표 함수 [람다식]을 이용한

        // 2. 검증된 토큰생성 [ 일반 유저 ]
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(memberEntity.getMrol() )
        );   // 토큰정보에 일반회원 내용 넣기

        // 3.
        MemberDto memberDto = memberEntity.toDto();  // 엔티티 --> Dto
        memberDto.setAuthorities( authorities );    // dto --> 토큰 추가
        return memberDto;  // dto 반환 [ ]    //빌드매니저가 비밀번호 검사함
        // 구현체 : 해당 인터페이스의 추상메소드[ 선언만 ] 구현한 클래스의 객체
    }

    // 비밀번호 찾기
    @Transactional
    public String getpassword(String memail) {
        // 1. 모든 레코드/엔티티 꺼내온다.
        List<MemberEntity> entityList = memberRepository.findAll();
        // 2. 리스트에 찾기
        for (MemberEntity entity : entityList) { // 리스트 반복
            if (entity.getMemail().equals(memail) ) { // 이메일이 같으면
                return  entity.getMpassword();
            }
        }
        return null;
    }
    // 3. 회원탈퇴
    @Transactional
    public int setdelete(String mpassword) {
        // 1. 로그인된 회원의 엔티티 필요
        // 1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");
        // 2. 세션 확인
        if (object != null) {
            int mno = (Integer) object; // 형변환 [ object --> int ]
            // 3. 세션에 있는 회원번호[PK] 로 리포지토리 찾기 [ findById : select * from member where mno = ? ]
            Optional<MemberEntity> optional = memberRepository.findById(mno);
            if (optional.isPresent()) {
                // Optional 클래스 :  null 관련 메소드 제공
                // 4. Optional 객체에서 데이터[엔티티] 빼오기
                MemberEntity entity = optional.get();
                // 5. 탈퇴
                // delete : delete from member where mno = ?   sql 문법과 같음
                memberRepository.delete(entity);
                // 6. 세션 [ 세셕삭제 = 로그아웃 ]
                request.getSession().setAttribute("loginMno", null);
                return 1;
            }
        }
        return 0; // [ 만약에 세션이 null 이면 반환 o 혹은 select 실패시 ]
    }

    // 5. 회원 수정
    @Transactional // 데이터 수정
    public  int setupdate(String mpassword){
        // 1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");
        // 2. 세션 존재여부 판단
        if( object != null ){
            int mno = (Integer)object;
            // 3. pk값을 가지고 엔티티[레코드] 검색
            Optional<MemberEntity> optional =  memberRepository.findById(mno);
            // 4. 검색된 결과 여부 판단
            if( optional.isPresent() ){ // 엔티티가 존재하면
                MemberEntity entity = optional.get();
                // 5. 찾은 엔티티의 필드값 변경 [ update member set 필드명 = 값 where  필드명 = 값 ]
                entity.setMpassword(mpassword);
                return  1;
            }
        }
        return 0;
    }

    // 6. 로그인 여부 판단 메소드
   /* public  int getloginMno(){
        // 1. 세션호출
        Object object = request.getSession().getAttribute("loginMno");
        // 2. 세션 여부 판단
        if( object != null ){ return (Integer) object; }
        else {return 0;}
    }*/
    // 6. 로그인 여부 판단 메소드
    public  String getloginMno() {
        // 1. 인증된 토큰 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 2. 인증된 토큰 내용 확인
        Object principal = authentication.getPrincipal();  // Principal : 접근주체 [ UserDeatils(MemberDto) ]
        System.out.println("토큰내용 확인 : " + principal);
        // 3. 토큰 내용에 따른 제어
        if (principal.equals("anonymousUser")) {  // anonymousUser 이면 로그인전
            return null;
        } else { // anonymousUser 아니면 로그인후
            MemberDto memberDto = (MemberDto) principal; //오브젝트임
            return memberDto.getMemail()+"_"+memberDto.getAuthorities();
        }
    }

    // 시큐리티 쓰기전
  /*  public  void logout(){
        request.getSession().setAttribute("loginMno", null);
    }*/


    // 8. 회원 목록 리스트
    public List<MemberDto> list(){
        // 1. JPA 이용한 모든 엔티티 호출
        List<MemberEntity> list = memberRepository.findAll();
        // 2. 엔티티 --> DTO
        // dto list 선언
        List<MemberDto> dtolist = new ArrayList<>();
        for(MemberEntity entity : list){
            dtolist.add( entity.toDto() );
        }
        return dtolist;
    }

    // 9. 인증코드 발송
    public String getauth( String toemail){
        String auth = "";
        String html = "<html><body><h1> Ezenweb 회원 가입 이메일 인증코드 입니다 </h1>";

        Random random = new Random(); // 랜덤번호 생성 1. 난수 객체
        for( int i = 0 ;  i<6 ; i++ ){ // 2.  6번회전
            //   char randchar = random.nextInt(10)+48;  // 48 ~ 57 : 0~9 숫자만
            char randchar = (char)(random.nextInt(26)+97);  // 97~122 : 알파벳 소문자
            auth += randchar;
        }
        html += "<div>인증코드 : "+auth+"</div>";
        html += "</body></html>";
        mailsend( toemail , "Ezenweb 인증코드" , html ); // 메일전송
        return auth; // 인증코드 반환
    }

    // 9. 메일 전송 서비스
    public  void mailsend( String toemail, String title , String content) {
        try {
            // 1. Mime 프로토콜 객체 생성
            MimeMessage message = javaMailSender.createMimeMessage(); // 1. Mime 프로토콜 객체 생성
            // 2. MimeMessageHelper 설정 객체 생성 new MimeMessageHelper( miem객체명 , 첨부파일여부 , 인코딩타입 )
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            // 3. 보내는 사람 정보
            mimeMessageHelper.setFrom("dlwhdgns47@naver.com", "Ezenweb");
            // 4. 받는 사람
            mimeMessageHelper.setTo(toemail);
            // 5. 메일 제목
            mimeMessageHelper.setSubject(title);
            // 6. 메일 내용
            mimeMessageHelper.setText(content.toString() , true); // HTML 형식
            // 7. 메일 전송
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("메일전송 실패 : " + e);}
    }



}


