import java.io.IOException;
import java.util.Map;

/**
 * Created by Денис on 08.03.2017.
 */
public class Main {
    public static void main(String[] args ) throws IOException {
        Process a = new ProcessBuilder("notepad.exe").start();
        ProcessBuilder ac = new ProcessBuilder();
        Map<String, String> a1 = ac.environment();
        System.out.println("Notepad.exe" + a1);
        System.out.println(a1.size());
        a.destroy();
        System.out.println("Process a destroyed.");
        a.destroyForcibly();
        System.out.println("Process a Forcibly destroyed.");





    }
}
