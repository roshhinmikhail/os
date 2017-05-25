package pipe;

import Main.Const;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Денис on 27.03.2017.
 */
public class Pipe {
    private BufferedReader PipedInputStream;
    private PrintWriter PipedOutputStream;
    private Socket socket;

    /**
     * Запрашивает у пользователя ник и организовывает обмен сообщениями с
     * сервером
     */
    public Pipe() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите IP для подключения к серверу.");
        System.out.println("Формат: xxx.xxx.xxx.xxx"); //127.0.0.1

        String ip = scan.nextLine();

        try {
            // Подключаемся в серверу и получаем потоки(in и out) для передачи сообщений
            socket = new Socket(ip, Const.Port);
            PipedInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PipedOutputStream  = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Введите свой ник:");
            PipedOutputStream.println(scan.nextLine());

            // Запускаем вывод всех входящих сообщений в консоль
            Resender resend = new Resender();
            resend.start();

            // Пока пользователь не введёт "exit" отправляем на сервер всё, что
            // введено из консоли
            String str = "";
            while (!str.equals("exit")) {
                str = scan.nextLine();
                PipedOutputStream.println(str);
            }
            resend.setStop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * Закрывает входной и выходной потоки и сокет
     */
    private void close() {
        try {
            PipedInputStream.close();
            PipedOutputStream.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Потоки не были закрыты!");
        }
    }

    /**
     * Класс в отдельной нити пересылает все сообщения от сервера в консоль.
     * Работает пока не будет вызван метод setStop().
     */
    private class Resender extends Thread {

        private boolean stoped;

        /**
         * Прекращает пересылку сообщений
         */
        public void setStop() {
            stoped = true;
        }

        /**
         * Считывает все сообщения от сервера и печатает их в консоль.
         * Останавливается вызовом метода setStop()
         */
        @Override
        public void run() {
            try {
                while (!stoped) {
                    String str = PipedInputStream.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении сообщения.");
                e.printStackTrace();
            }
        }
    }

}

