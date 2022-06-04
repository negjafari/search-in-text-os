import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        int threads = 4;
        FileManager fileManager = new FileManager(threads);
        fileManager.getInputFiles();
        ArrayList<String> files = fileManager.fileSplitter();
        String[] words = fileManager.createWordsArray();

        for (int i = 1; i < 5; i++) {
            String fileName = "file" + "_" + i;
            new Thread(new ThreadManager(words, fileName,"result.txt")).start();
        }














//
//        int n = 8; // Number of threads
//        for (int i = 0; i < n; i++) {
//            MThread object
//                    = new MThread();
//            object.start();
//        }
    }






}
