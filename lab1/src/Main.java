import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by Денис on 07.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        //Указываем путь к созданному файлу
        Path path = Paths.get("D:\\ProgramFiles\\Programm\\OC\\lab1\\file.txt");

        try {
            System.out.println("Информация о файле: ");
            System.out.println("\t Имя файла: " + path.getFileName());
            System.out.println("\t Корневая папка: " + path.getRoot());
            System.out.println("\t Весь путь к файлу: " + path.getParent());

            Object object = Files.getAttribute(path, "creationTime");
            System.out.println("\t Время и дата создания: " + object);

            object = Files.getAttribute(path, "lastModifiedTime",
                    LinkOption.NOFOLLOW_LINKS);
            System.out.println("\t Последняя модификация: " + object);

            object = Files.getAttribute(path, "size");
            System.out.println("\t Размер: " + object);

            object = Files.getAttribute(path, "isDirectory");
            System.out.println("\t Каталог: " + object);

            Calendar s = Calendar.getInstance();
            s.set(1960,Calendar.MARCH,20);

            object = Files.setAttribute(path,"creationTime", FileTime.fromMillis(s.getTimeInMillis()));
            object = Files.getAttribute(path, "creationTime");
            System.out.println("\t Время и дата создания: " + object);

            Calendar m = Calendar.getInstance();
            m.set(2017,Calendar.MARCH,20);

            object = Files.setLastModifiedTime(path, FileTime.fromMillis(m.getTimeInMillis()));
            object = Files.getAttribute(path, "lastModifiedTime");
            System.out.println("\t Последняя модификация: " + object);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
