package Main;
/**
 * Created by Денис on 08.03.2017.
 */

import client.Client;
import pipe.Pipe;
import server.Server;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Запустить программу в режиме сервера или клиента? (S(erver) / C(lient)/ P(ipe)");
        while (true) {
            char answer = Character.toLowerCase(in.nextLine().charAt(0));
            if (answer == 's') {
                new Server();
                break;
            } else if (answer == 'c') {
                new Client();
                break;
            }else if (answer == 'p') {
                new Pipe();
                break;
            } else {
                System.out.println("Некорректный ввод. Повторите.");
            }
        }
    }

}
