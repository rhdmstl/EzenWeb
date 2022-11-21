package Com.EzenWeb.Domain.Dto;

import Com.EzenWeb.Domain.entity.bcategory.BcategoryEntyti;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BcategoryDto {
    private int bcno;  // 카테고리 번호
    private  String bcname;  // 카테고리이름

    public BcategoryEntyti toEntity(){
        return BcategoryEntyti.builder()
               .bcno(this.bcno) //this 해당 메소드를 호출하는 객체의 필드
               .bcname(this.bcname)
               .build();
    }
}
