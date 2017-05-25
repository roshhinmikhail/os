import Main.Const;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class PipeExample {

    private static BufferedReader in;
    private static PrintWriter out;
    private static Socket socket;

    public static void main(String[] args) throws IOException {

        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream  input  = new PipedInputStream(output);
        Scanner scan = new Scanner(System.in);
        final String[] str = {""};



        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Введите IP для подключения к серверу.");
                System.out.println("Формат: xxx.xxx.xxx.xxx");
                String ip = scan.nextLine();
                try {

                    socket = new Socket(ip, Const.Port);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);

                    System.out.println("Введите свой ник:");
                    str[0] = scan.nextLine();
                    String a = str[0];
                    output.write("Input text".getBytes());
                    output.write("\n".getBytes());


                    //output.write("Hello pipe!\n".getBytes());

                    while (!str[0].equals("exit")) {
                        str[0] = scan.nextLine();
                        output.write(a.getBytes());
                        output.write(":".getBytes());
                        output.write(str[0].getBytes());
                        output.write("\n".getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = input.read();
                    while(data != -1){
                        System.out.print((char) data);
                        data = input.read();

                    }
                } catch (IOException e) {
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}