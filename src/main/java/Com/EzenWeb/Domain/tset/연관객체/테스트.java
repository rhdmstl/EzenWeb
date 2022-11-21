package Com.EzenWeb.Domain.tset.연관객체;

public class 테스트 {
    public static void main(String[] args) {
        // 1. 학급 객체 생성
        학급 학급1 = new 학급();
        학급1.학급명 = "초등학생";

        // 2. 학생 객체 생성
        학생 학생1 = new 학생();
        학생1.학생명 = "유재석";

        // 3. 단방향
        학생1.학급 = 학급1;
        // 4. 양방향
        학급1.학생리스트.add(학생1);
        // 5. Join 기능
        // 1. 학급에서 학생 조회 가능
        System.out.println(학급1.학생리스트.get(0).학생명);
        // 2. 학생이 학급 조회 가능
        System.out.println(학생1.학급.학급명);


    }

}
