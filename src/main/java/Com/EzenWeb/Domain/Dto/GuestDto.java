
package Com.EzenWeb.Domain.Dto;

import Com.EzenWeb.Domain.entity.guset.GuestEntity;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class GuestDto {
    private int bgno;
    private String bgtitle;
    private String bgcontent;
    private int bgcno; //카테고리[fk]

    public GuestEntity toEntity() {
        return GuestEntity
                .builder()
               .bgno(this.bgno)
               .bgtitle(this.bgtitle)
               .bgcontent(this.bgcontent)
               .build();
    }
}

