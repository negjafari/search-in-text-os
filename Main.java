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

        //mutex lock
        Runnable task = new ThreadManagerWithMutex(words,"result.txt");
        for (int i = 1; i < threads + 1; i++) {
            Thread thread = new Thread(task, "thread" + i);
            thread.start();
        }



//        Semaphore semaphore = new Semaphore(1);
//        for(int i = 1 ; i<threads + 1 ; i++){
//            String fileName = "thread" + i;
//            ThreadManagerWithSemaphore thread = new ThreadManagerWithSemaphore(words, "result.txt",fileName, semaphore);
//            thread.start();
//        }


//        Runnable threadManager = new ThreadManagerWithSemaphore(words, "result.txt");
//
//        for (int i = 1; i < threads + 1; i++) {
//            Thread thread = new Thread(threadManager, "thread" + i);
//            thread.start();
//        }




//        for (int i = 1; i < threads + 1; i++) {
//            String fileName = "file" + "_" + i;
//            new Thread(new ThreadManager(words, fileName,"result.txt"), "thread1").start();
//        }


    }
}
