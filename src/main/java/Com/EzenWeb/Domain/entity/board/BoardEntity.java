package Com.EzenWeb.Domain.entity.board;

import Com.EzenWeb.Domain.Dto.BoardDto;
import Com.EzenWeb.Domain.entity.BaseEntity;
import Com.EzenWeb.Domain.entity.bcategory.BcategoryEntyti;
import Com.EzenWeb.Domain.entity.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="board")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder //생성자 없이 메모리할당
public class BoardEntity extends BaseEntity {
    @Id // pk
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto
    private int bno;            // 게시물번호
    @Column( nullable = false )     // not null
    private String btitle;      // 게시물제목
    @Column( nullable = false , columnDefinition = "TEXT")     // not null , DB 자료형사용시 columnDefinition = "DB자료형"
    private String bcontent;    // 게시물 내용
    @Column( nullable = false )     // not null
    @ColumnDefault( "0" )           // JPA insert 할 경우 default
    private int bview;          // 조회수
    @Column
    private String bfile;       // 첨부파일

    // 작성일,수정일 -> 상속( 여러 엔티티해서 사용되는 필드라서 )

    // 연관관계
    @ManyToOne //fk [1: n]
    @JoinColumn(name = "mno") //테이블에서 사용할 fk의 필드명 정의
    @ToString.Exclude // ToString()을 사용하지않음
    private MemberEntity memberEntity; //pk 엔티티객체

    //연관관계2
    @ManyToOne //fk
    @JoinColumn(name = "bcno")
    @ToString.Exclude
    private BcategoryEntyti bcategoryEntity;

    //형변환_빌더패턴을 이용한 객체생성[builder=시작,build=끝](메소드)
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .btitle( this.btitle)
                .bcontent( this.bcontent)
                .bview( this.bview)
                .memail( this.memberEntity.getMemail())
                .build();
    }
}
/*
    int                 int
    double,float        float
    String              varchar
        columnDefinition = "DB자료형" DB자료형사용가능
    빌더패턴은 순서상관없이 객체를 만들어서 안전성을 추가해줌
*/
/*
    연관관계
    @OneToOne        1 : 1      회원이 하나의 게시물만 작성 가능
    @ManyToOne       n : 1      회원이 여러개의 게시물을 작성 가능
    @OneToMany       1 : n
    @ManyToMany      n : n
 */