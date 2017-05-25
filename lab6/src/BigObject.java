import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 15.03.2017.
 */

class BigObject {
    private int value;



    public BigObject(Integer pValue) {
            value = pValue;
        }

        @Override
        protected void finalize () throws Throwable {
            System.out.println("BigObject (" + value + ") finalize()");
        }

        @Override
        public String toString () {
            return value + "";
        }


        static List<BigObject> strongList; // сильные ссылки
        static List<Reference<BigObject>> softList; // мягкие ссылки
        static List<Reference<BigObject>> weakList; // слабые ссылки
        static List<Reference<BigObject>> phantomList; // фантомные ссылки
        static ReferenceQueue<BigObject> queue; // очередь
        static List<String> loadMemoryList; // здесь будут храниться ссылки на строки, которые используются для загрузки памяти

    private static void printLists() {
        System.out.println("Strong references: ");
        for (BigObject bo : strongList) System.out.print(bo + " ");
        System.out.println();
        System.out.println("SoftReferences: ");
        printList(softList);
        System.out.println("WeakReferences: ");
        printList(weakList);
        System.out.println("PhantomReferences: ");
        printList(phantomList);
    }

    private static void printList(List<Reference<BigObject>> pList) {
        for (Reference<BigObject> ref : pList)
            System.out.print(ref.get() + " ");

        System.out.println();
    }

    private static void init() {
        strongList = new ArrayList<BigObject>();
        softList = new ArrayList<Reference<BigObject>>();
        weakList = new ArrayList<Reference<BigObject>>();
        phantomList = new ArrayList<Reference<BigObject>>();
        loadMemoryList = new ArrayList<String>();
        queue = new ReferenceQueue<BigObject>();
        for (int i = 0; i < 3; i++) {
            strongList.add(new BigObject(i));
            softList.add(new SoftReference<BigObject>(new BigObject(i)));

            weakList.add(new WeakReference<BigObject>(new BigObject(i)));
            phantomList.add(new PhantomReference<BigObject>(new BigObject(i), queue));
        }
        printLists();
    }


    private static void loadMemory() {
        for (int i = 0; i < 500; i++) {
            loadMemoryList.add(i + "");
        }
    }
    public static void main(String[] args) {

        init();
        System.gc();
        System.out.println("Первый вызов сборщика мусора");
        printLists();
        System.out.println("Использование памяти при загрузке");
        loadMemory(); // загрузка памяти
        System.out.println("loadMemoryList.size() = " + loadMemoryList.size());
        System.gc();
        System.out.println("Второй вызов сборщика мусора");
        printLists();
    }
}
