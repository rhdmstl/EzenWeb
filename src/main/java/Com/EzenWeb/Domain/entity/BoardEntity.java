package Com.EzenWeb.Domain.entity;

import Com.EzenWeb.Domain.Dto.BoardDto;
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
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column( nullable = false)
    private String btitle;
    @Column( nullable = false , columnDefinition = "text")//not null,저장길이(기본이 250)
    private String bcontent;

    //작성/수정 -> 상속
    @Column(nullable = false)
    @ColumnDefault("0")         //JPA insert 할때 default
    private int bview; //조회수
    private String bfile; //첨부파일
//----fk--------------------------------//
    @Column(nullable = false)
    private int mno; //작성자[fk]
    @Column(nullable = false)
    private int cno; //카테고리[fk]

    //형변환_빌더패턴을 이용한 객체생성[builder=시작,build=끝](메소드)
    public BoardDto todto(){
        return BoardDto.builder()
                .mno(this.mno)
                .btitle( this.btitle)
                .bcontent( this.bcontent)
                .bview( this.bview)
                .bfile( this.bfile)
                .mno( this.mno)
                .cno( this.cno)
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