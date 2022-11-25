package Com.EzenWeb.Domain.Dto;

import Com.EzenWeb.Domain.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile bfile; //첨부파일
    private String bfilename; //첨부파일 호출용
    //----fk--------------------------------//
    private int mno; //작성자[fk]
    private int bcno;
    private String memail;

    private int startbtn; // 시작버튼
    private int endbtn; //끝버튼

    //형변환_생성자를 이용한 객체생성[builder=시작,build=끝]
    //생성자를 이용한 객체사용할때는 순서가 틀리면 깨지고 풀생성자가 아니면 또 만들어줘야함
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .build();
    }
}