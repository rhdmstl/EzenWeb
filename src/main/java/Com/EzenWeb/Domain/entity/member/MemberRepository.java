package Com.EzenWeb.Domain.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository    //인터페이스명                           <엔티티클래스명,PK필드자료형>
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {

    Optional<MemberEntity> findByMemail(String memail);

}
