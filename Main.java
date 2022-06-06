import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {

        System.out.println("Enter number of threads ... ");
        Scanner sc = new Scanner(System.in);
        int threads = sc.nextInt();

        FileManager fileManager = new FileManager(threads);
        fileManager.getInputFiles();
        ArrayList<Integer> files = fileManager.fileSplitter();
        String[] words = fileManager.createWordsArray();




        ////one thread
//        long thread_start = System.nanoTime();
//        Runnable task = new ThreadManager(words,"result.txt");
//        for (int i = 1; i < threads + 1; i++) {
//            Thread thread = new Thread(task, "thread" + i);
//            thread.start();
//        }
//        long thread_end = System.nanoTime();
//        double total_time = (thread_end - thread_start)/1000000000.000000000;
//        System.out.println("thread_start = " + thread_start);
//        System.out.println("thread_end = " + thread_end);
//        System.out.println("total_time = " + total_time);


        ////10 threads
//        long threads_start = System.nanoTime();
//        Runnable task = new ThreadManager(words,"result.txt");
//        for (int i = 1; i < threads + 1; i++) {
//            Thread thread = new Thread(task, "thread" + i);
//            thread.start();
//        }
//        long threads_end = System.nanoTime();
//        double total_time = (threads_end - threads_start)/1000000000.000000000;
//        System.out.println("thread_start = " + threads_start);
//        System.out.println("thread_end = " + threads_end);
//        System.out.println("total_time = " + total_time);


        ////mutex lock
//        long mutex_start = System.nanoTime();
//        Runnable task = new ThreadManagerWithMutex(words,"result.txt");
//        for (int i = 1; i < threads + 1; i++) {
//            Thread thread = new Thread(task, "thread" + i);
//            thread.start();
//        }
//        long mutex_end = System.nanoTime();
//        double total_time = (mutex_end - mutex_start)/1000000000.000000000;
//        System.out.println("mutex_start = " + mutex_start);
//        System.out.println("mutex_end = " + mutex_end);
//        System.out.println("total_time = " + total_time);




        ////semaphore
        long semaphore_start = System.nanoTime();
        Semaphore semaphore = new Semaphore(threads);
        Runnable task = new ThreadManagerWithSemaphore(words, "result.txt", semaphore);
        for(int i=1 ; i<threads ; i++) {
            Thread thread = new Thread(task, "thread" + i);
            thread.start();
        }
        long semaphore_end = System.nanoTime();
        double total_time = (semaphore_end - semaphore_start)/1000000000.000000000;
        System.out.println("semaphore_start = " + semaphore_start);
        System.out.println("semaphore_end = " + semaphore_end);
        System.out.println("total_time = " + total_time);





    }
}
