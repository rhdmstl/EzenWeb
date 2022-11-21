package Com.EzenWeb.Domain.tset;

public class 싱글톤 {
    private static 싱글톤 singleton = new 싱글톤();
    public static 싱글톤 getSingleton() {
        return singleton;
    }
}
