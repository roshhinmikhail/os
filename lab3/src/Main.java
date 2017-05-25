/**
 * Created by Денис on 08.03.2017.
 */
public class Main {
    public static void main(String args []){
       Counter th = new Counter();
       new Thread(th,"Поток 1 ").start();
       new Thread(th,"Поток 2 ").start();
    }
}

class Counter implements Runnable {
    synchronized public void run() {
        int i;
        for (i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName() + "цикл:" + i);
        }
    }
}