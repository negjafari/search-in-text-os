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

        ArrayList<String> test = new ArrayList<>();

        for (int i = 1; i < threads + 1; i++) {
            String fileName = "file" + "_" + i;
            new Thread(new ThreadManager(words, fileName,"result.txt")).start();
        }


    }
}
