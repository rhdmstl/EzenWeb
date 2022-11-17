package Com.EzenWeb.Domain.entity;


import Com.EzenWeb.Domain.Dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //해당연결된 db의 테이블과 매핑
@Table(name = "member")//테이블명
@NoArgsConstructor //빈생성자
@AllArgsConstructor ///플생성자
@Getter //게터
@Setter //세터
@ToString //투스트링
@Builder //객체생성 안전성보장
public class MemberEntity {
    //필드
    @Id //엔티티당 무조건 1개이상
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동번호 부여
    private int mno;

    @Column(nullable = false) //not null
    private String memail; //회원아이디
    @Column(nullable = false) //not null
    private String mpassword;//회원비밀번호

    @Column(nullable = false) //not null
    private String mphone; //회원전화번호

    public MemberDto toDto(){
        return MemberDto
                .builder() // 빌더 메소드 시작
                .mno( this.mno )
                .memail(this.memail)
                .mpassword( this.mpassword )
                .mphone( this.mphone)
                .build(); // 빌드 메소드 끝
    }
    //생성자

    //메소드

}

