package Com.EzenWeb.Domain.tset.연관객체;

public class 제품테스트 {
    public static void main(String[] args) {
        제품 제품1 = new 제품();
        제품1.제품명 = "나이키신발";

        // 2. 이미지 객체 [ fk ] 생성
        이미지 이미지1 = new 이미지();
        이미지1.이미지명 = "나이키신발1.jpg";

        이미지 이미지2 = new 이미지();
        이미지2.이미지명 = "나이키신발2.jpg";

        // 3. 이미지 객체 [ FK ] ----> 제품 객체 [ PK ]대입
        이미지1.제품 = 제품1;
        이미지2.제품 = 제품1;

        제품1.이미지List.add(이미지1);
        제품1.이미지List.add(이미지2);
    }
}
