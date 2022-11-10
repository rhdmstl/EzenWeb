package Com.EzenWeb.Domain.Dto;

import lombok.*;

@NoArgsConstructor //빈생성자
@AllArgsConstructor ///플생성자
@Getter //게터
@Setter //세터
@ToString //투스트링
@Builder //객체생성 안전성보장
public class MemberDto {
    private String name;
    private String email;
    private String organization;
}
