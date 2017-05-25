import java.io.IOException;

/**
 * Created by Денис on 15.03.2017.
 */
public class Turn_off {
    public static void main(String arg[]) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("shutdown -s -t 0");
        System.exit(0);
    }
}
