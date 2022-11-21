package Com.EzenWeb.Domain.tset.연관객체;

import lombok.ToString;

import javax.persistence.ManyToOne;
@ToString
public class 이미지 {
    String  이미지명;
    제품 제품;
}
