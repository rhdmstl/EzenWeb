package Com.EzenWeb.Domain.Dto;

import lombok.*;

import java.util.Map;

@NoArgsConstructor //빈생성자
@AllArgsConstructor ///플생성자
@Getter //게터
@Setter //세터
@ToString //투스트링
@Builder //객체생성 안전성보장
public class OathDto {
    private String memail;
    private String mname;
    private String registrationId;  //회사명
    private Map<String,Object> attributes; //인증결과
    private String oath2UserInfo;   //회원정보

                            //회사명                   회원정보                인증된 토큰
    public static OathDto of(String registrationId , String oath2UserInfo , Map<String,Object> attributes){
        if (registrationId.equals("kakao")){ return ofKakao(registrationId , oath2UserInfo , attributes); }
        else if(registrationId.equals("naver")){ return ofNavero(registrationId , oath2UserInfo , attributes); }
        else if (registrationId.equals("google")) { return ofGoogle(registrationId , oath2UserInfo , attributes); }
        else { return null; }
    }

    //카카오객체 생성 메소드
    public static OathDto ofKakao(String registrationId , String oath2UserInfo , Map<String,Object> attributes){
        //1.회원정보 호출
        Map<String , Object> Kakao_acount = (Map<String, Object>) attributes.get(oath2UserInfo);
        Map<String,Object> profile = (Map<String, Object>) Kakao_acount.get("profile");
        return OathDto.builder()
                .memail((String) Kakao_acount.get("email"))
                .mname((String) Kakao_acount.get("nickname"))
                .registrationId(registrationId)
                .oath2UserInfo(oath2UserInfo)
                .attributes(attributes)
                .build();
    }
    //네이버객체 생성 메소드
    public static OathDto ofNavero(String registrationId , String oath2UserInfo , Map<String,Object> attributes){
        Map<String,Object> response = (Map<String, Object>) attributes.get(oath2UserInfo);
        return OathDto.builder()
                .memail((String) response.get("email"))
                .mname((String) response.get("nickname"))
                .registrationId(registrationId)
                .oath2UserInfo(oath2UserInfo)
                .attributes(attributes)
                .build();
    }
    //구글객체 생성 메소드
    public static OathDto ofGoogle(String registrationId , String oath2UserInfo , Map<String,Object> attributes){

        return OathDto.builder()
                .memail((String) attributes.get("email"))
                .mname((String) attributes.get("name"))
                .registrationId(registrationId)
                .oath2UserInfo(oath2UserInfo)
                .attributes(attributes)
                .build();
    }
}
