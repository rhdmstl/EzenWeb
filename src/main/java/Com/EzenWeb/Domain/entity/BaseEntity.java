package Com.EzenWeb.Domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass //상속받을 경우 자식 클래스에게 매칭 정보 전달
@EntityListeners(AuditingEntityListener.class) //감시기능
public class BaseEntity {
    @CreatedDate //데이터 생성날짜
    @Column(updatable = false) //수정불가
    private LocalDateTime cdate;

    @LastModifiedDate //데이터 수정날짜
    private LocalDateTime udate;

}
