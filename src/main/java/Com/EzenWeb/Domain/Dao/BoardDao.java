package Com.EzenWeb.Domain.Dao;

import Com.EzenWeb.Domain.Dto.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public BoardDao() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root",
                    "1234");
            System.out.println("DB 연동 성공");
        } catch (Exception e) { System.out.println("실패");}
    }
    public boolean setboard(BoardDto dto) {
        String sql = " insert into board(btitle,bcontent) values(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getBtitle());
            ps.setString(2, dto.getBcontent());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {System.out.println("게시물 가져오기 오류" + e);}
            return false;
    }
    //게시물 출력
    public ArrayList<BoardDto> getboards() {
        ArrayList<BoardDto> boards = new ArrayList<BoardDto>();
        String sql = " select * from board";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                boards.add(
                        BoardDto.builder()
                                .btitle(rs.getString(1))
                                .bcontent(rs.getString(2))
                                .build()
                );
            }
        }catch (Exception e) {System.out.println("출력오류"+e);}
             return boards;
    }

}

