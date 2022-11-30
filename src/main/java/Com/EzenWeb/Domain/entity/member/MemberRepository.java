package Com.EzenWeb.Domain.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository    //인터페이스명                           <엔티티클래스명,PK필드자료형>
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {

    //@ID필드의 자료형>
    // * findBy필드명(데이터) 필드검색
    // 1. 이메일을 이용한 엔티티 검색 메소드
    Optional<MemberEntity> findByMemail(String memail);

}
