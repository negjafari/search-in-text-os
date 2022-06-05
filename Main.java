import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) {

        System.out.println("Enter number of threads ... ");
        Scanner sc = new Scanner(System.in);
        int threads = sc.nextInt();

        FileManager fileManager = new FileManager(threads);
        fileManager.getInputFiles();
        ArrayList<String> files = fileManager.fileSplitter();
        String[] words = fileManager.createWordsArray();

        ////mutex lock
//        Runnable task = new ThreadManagerWithMutex(words,"result.txt");
//        for (int i = 1; i < threads + 1; i++) {
//            Thread thread = new Thread(task, "thread" + i);
//            thread.start();
//        }


        //semaphore
        Semaphore semaphore = new Semaphore(1);
        Runnable task = new ThreadManagerWithSemaphore(words, "result.txt", semaphore);
        for(int i=1 ; i<threads ; i++) {
            Thread thread = new Thread(task, "thread" + i);
            thread.start();
        }



    }
}
