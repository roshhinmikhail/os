/**
 * Created by Денис on 08.03.2017.
 */
public class Main {
    public static void main(String args []) throws InterruptedException {
         Counter1 th1 = new Counter1();
        new Thread(th1,"Поток 1 ").sleep(100);
        new Thread(th1,"Поток 1 ").start();
        new Thread(th1,"Поток 2 ").start();

        Counter3 th3 = new Counter3();
        new Thread(th3,"Поток 3 ").sleep(250);
        new Thread(th3,"Поток 3 ").start();

        Counter4 th4 = new Counter4();
        new Thread(th4,"Поток 4 ").start();


    }
}

class Counter1 implements Runnable {
    synchronized public void run() {
        int i;
        int a = 0;
        for (i=0; i<100; i++){
            a = a+2;
            System.out.println(Thread.currentThread().getName() + "цикл:" + i + " значение" + a);
        }
    }
}

class Counter3 implements Runnable {
    public void run() {
        int i;
        for (i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName() + "цикл:" + i);
        }
    }
}

class Counter4 implements Runnable {
    public void run() {
        int i;
        for (i=0; i>-1000; i = i-3){
            System.out.println(Thread.currentThread().getName() + "цикл:" + i);
        }
    }
}
