import java.io.File;
import java.io.IOException;


/**
 * Created by vodol on 16.03.2017.
 */
public class Main {
    private static final String FILENAME = "D:\\ProgramFiles\\Programm\\OC\\lab7\\file.txt";

    public static void main(String[] args) {

        File file = new File(FILENAME);
        System.out.println("Имя файла: " + FILENAME);

        if (file.exists()) {
            System.out.println("Файл существует.");

            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);

            System.out.println("Файл доступен для выполнения: " + file.canExecute());
            System.out.println("Файл доступен для чтения: " + file.canRead());
            System.out.println("Файл доступен для записи: " + file.canWrite());
        }

        System.out.println();
        System.out.println("Установка прав на файл.");

        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(false);

        System.out.println("Файл доступен для выполнения: " + file.canExecute());
        System.out.println("Файл доступен для чтения: " + file.canRead());
        System.out.println("Файл доступен для записи: " + file.canWrite());

    }
}
