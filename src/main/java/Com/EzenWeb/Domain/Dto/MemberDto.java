package Com.EzenWeb.Domain.Dto;

import Com.EzenWeb.Domain.entity.MemberEntity;
import lombok.*;

@NoArgsConstructor //빈생성자
@AllArgsConstructor ///플생성자
@Getter //게터
@Setter //세터
@ToString //투스트링
@Builder //객체생성 안전성보장
public class MemberDto {
    private int mno;
    private String memail;
    private String mpassword;

    private String mphone;

    //dto --> entity로 변환
    public  MemberEntity toEntity(){
        return MemberEntity.builder()
                    .mno(this.mno)
                    .memail(this.memail)
                    .mpassword(this.mpassword)
                    .mphone(this.mphone)
                    .build();
    }
}
