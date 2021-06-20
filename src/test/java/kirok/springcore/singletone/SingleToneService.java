package kirok.springcore.singletone;

import net.bytebuddy.build.Plugin.Engine.Target.Sink;

public class SingleToneService {

    private static final SingleToneService instance = new SingleToneService();

    private SingleToneService() {
        System.out.println("생성함");
    }

    public static SingleToneService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤");
    }

}
