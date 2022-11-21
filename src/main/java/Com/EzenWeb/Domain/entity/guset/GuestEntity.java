
package Com.EzenWeb.Domain.entity.guset;

import Com.EzenWeb.Domain.Dto.GuestDto;
import Com.EzenWeb.Domain.entity.guestcategory.GuestCategoryEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity @Table(name = "guest")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bgno;            // 게시물번호
    @Column( nullable = false )     // not null
    private String bgtitle;      // 게시물제목
    @Column( nullable = false , columnDefinition = "TEXT")     // not null , DB 자료형사용시 columnDefinition = "DB자료형"
    private String bgcontent;    // 게시물 내용

    @ManyToOne
    @JoinColumn(name = "bgcno")
    @ToString.Exclude
    private GuestCategoryEntity guestCategoryEntity;

    public GuestDto toDto() {
        return GuestDto
                .builder()
                .bgno(this.bgno)
                .bgtitle(this.bgtitle)
                .bgcontent(this.bgcontent)
                .build();
    }
}

