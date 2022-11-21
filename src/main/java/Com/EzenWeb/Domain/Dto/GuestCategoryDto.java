
package Com.EzenWeb.Domain.Dto;

import Com.EzenWeb.Domain.entity.guestcategory.GuestCategoryEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GuestCategoryDto {
    private int bgcno;  // 카테고리 번호
    private  String bgcname;  // 카테고리이름

    public GuestCategoryEntity toEntity() {
        return GuestCategoryEntity.builder()
               .bgcno(this.bgcno)
               .bgcname(this.bgcname)
               .build();
    }
}

