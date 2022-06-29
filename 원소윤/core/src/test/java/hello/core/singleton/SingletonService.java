package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    } //여기서만 객체 인스턴스 조회 가능

    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");

    }
}
