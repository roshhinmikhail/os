import java.io.File;
import java.io.IOException;

/**
 * Created by Денис on 07.03.2017.
 */

public class CreateFile {
    public static void main (String args [])throws IOException{
        String absoluteFilePath = "file.txt";
        File file = new File(absoluteFilePath);
        if(file.createNewFile()){
            System.out.println(absoluteFilePath + " Файл создан");
        } else {
            System.out.println("Файл " + absoluteFilePath + " уже существует");

        }
    }
}
