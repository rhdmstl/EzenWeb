package Com.EzenWeb.Domain.entity.bcategory;


import Com.EzenWeb.Domain.Dto.BcategoryDto;
import Com.EzenWeb.Domain.entity.board.BoardEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bcategory")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class BcategoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) // 자동번호 부여
    private int bcno;  // 카테고리 번호
    @Column(nullable = false) //not null
    private  String bcname;  // 카테고리이름

    @OneToMany(mappedBy = "bcategoryEntity")
    @Builder.Default
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    public BcategoryDto toDto() {
        return BcategoryDto.builder()
                .bcno(this.bcno)
                .bcname(this.bcname)
                .build();
    }
}
