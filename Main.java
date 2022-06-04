import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class MThread extends Thread {

    public MThread() {

    }

    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                    "Thread " + Thread.currentThread().getId()
                            + " is running");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        int threads = 4;
        FileManager fileManager = new FileManager(threads);
        fileManager.getInputFiles();
        ArrayList<String> files = fileManager.fileSplitter();

//        for (String s : list) {
//            System.out.println(s);
//        }










//
//        int n = 8; // Number of threads
//        for (int i = 0; i < n; i++) {
//            MThread object
//                    = new MThread();
//            object.start();
//        }
    }






}
