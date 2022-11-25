package Com.EzenWeb.Domain.entity.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    // 1. 기본메소드 외 메소드 추가
    // 1. .findById( pk값 ) : 해당 pk의 엔티티 하나 호출
    // 2. .findAll()       : 모든 엔티티를 호출
    // 3. [ 직접 find 만들기 ] :  .findby필드명( 조건 )     [ Optional<엔티티명> ]
    // 3                        .findby필드명( 조건 )     [ 엔티티명    ]
    // 3                        .findby필드명( 조건 )     [ List<엔티티명>    ]
    // 3.                       .findby필드명( 조건 , Pageable pageable  )     [ Page<엔티티명> ]

    // 1. @Query( value = "쿼리문작성" , nativeQuery = true )
    // SQL[쿼리문] 변수 넣기
    //  [ 인수 ] ( @Param("변수명") 자료형 변수명 )   ----------->    :변수명
    //  [ 인수 ] ( 자료형 변수명 ,자료형 변수명    )   ----------->    ?인수번호
    // @Param("변수명") 생략가능 [ jdk 8 이상 ]
    // 1. 제목 검색
    @Query( value = "select * from board where bcno = :bcno and btitle like %:keyword%" , nativeQuery = true )
    Page<BoardEntity> findbybtitle( int bcno , String keyword , Pageable pageable);
    // 2. 내용 검색
    @Query( value = "select * from board where bcno = :bcno and bcontent like %:keyword%" , nativeQuery = true )
    Page<BoardEntity> findbybcontent( int bcno , String keyword , Pageable pageable);
    // 3. 검색이 없을때
    @Query( value = "select * from board where bcno = :bcno " , nativeQuery = true)
    Page<BoardEntity> findBybcno(@Param("bcno") int bcno , Pageable pageable);
}
