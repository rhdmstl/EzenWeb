package Com.EzenWeb.Domain.Dto;

import Com.EzenWeb.Domain.entity.BoardEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;

    //작성/수정 -> 상속
    private int bview; //조회수
    private String bfile; //첨부파일
    //----fk--------------------------------//
    private int mno; //작성자[fk]
    private int cno; //카테고리[fk]

    //형변환_생성자를 이용한 객체생성[builder=시작,build=끝]
    //생성자를 이용한 객체사용할때는 순서가 틀리면 깨지고 풀생성자가 아니면 또 만들어줘야함
    public BoardEntity toEntity(){
        return new BoardEntity(
                this.bno,
                this.btitle,
                this.bcontent ,
                this.bview,
                this.bfile,
                this.mno,
                this.cno);
    }
}


