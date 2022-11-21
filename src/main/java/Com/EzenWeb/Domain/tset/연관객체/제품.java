package Com.EzenWeb.Domain.tset.연관객체;

import lombok.ToString;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@ToString
public class 제품 {
    @Id
    String 제품명;
    @OneToMany(mappedBy = "제품" , cascade = CascadeType.ALL)
    @ToString.Exclude
    List<이미지> 이미지List = new ArrayList<>();
}
