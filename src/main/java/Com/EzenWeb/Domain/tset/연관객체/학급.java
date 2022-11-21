package Com.EzenWeb.Domain.tset.연관객체;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
public class 학급 {
    String 학급명;
    @OneToMany
    List<학생> 학생리스트 = new ArrayList<>();
}
