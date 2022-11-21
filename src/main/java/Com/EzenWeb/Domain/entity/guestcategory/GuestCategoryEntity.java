
package Com.EzenWeb.Domain.entity.guestcategory;


import Com.EzenWeb.Domain.Dto.GuestCategoryDto;
import Com.EzenWeb.Domain.entity.guset.GuestEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "bgcategory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class GuestCategoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) // 자동번호 부여
    private int bgcno;  // 카테고리 번호
    @Column(nullable = false) //not null
    private  String bgcname;  // 카테고리이름

    @OneToMany(mappedBy = "bgno")
    @Builder.Default
    private List<GuestEntity> guestEntity = new ArrayList<>();

    public GuestCategoryDto toDto() {
        return GuestCategoryDto
                .builder()
                .bgcno(this.bgcno)
                .bgcname(this.bgcname)
                .build();
    }
}

