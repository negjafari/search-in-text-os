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


        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file name : ");
        String inputFileName = sc.nextLine();

        System.out.println("Enter word's file name : ");
        String wordsFileName = sc.nextLine();

        File inputFile = new File(inputFileName);
        if (inputFile.exists()) {
            System.out.println("File size in bytes " + inputFile.length());
        }

        File wordsFile = new File(wordsFileName);
        if (wordsFile.exists()) {
            System.out.println("Word's file size in bytes " + wordsFile.length());
        }

        String newFilesName = "file";
        int filesNumber = 4;


        ArrayList<String> files = fileSplitter(inputFileName, newFilesName, filesNumber);

        for (String file : files) {
            System.out.println(file);
        }








//
//        int n = 8; // Number of threads
//        for (int i = 0; i < n; i++) {
//            MThread object
//                    = new MThread();
//            object.start();
//        }
    }




    public static ArrayList<String> fileSplitter(String sourceFileName, String NewFilesName, int filesNumber) {

        int index=1;

        ArrayList<String> files = new ArrayList<>();

        File f = new File(sourceFileName);

        int size =  (int) (f.length()/(filesNumber));

        byte[] buffer = new byte[size];

        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f))){
            int tmp = 0;
            while ((tmp = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name

                if(index == filesNumber+1){
                    int j = index-1;
                    try (FileOutputStream out = new FileOutputStream(NewFilesName+"_"+j, true)) {
                        out.write(buffer, 0, tmp);//tmp is chunk size
                    }
                }
                else {
                    String path = NewFilesName+"_"+index;
                    File newFile = new File(path);
                    files.add(path);
                    try (FileOutputStream out = new FileOutputStream(newFile)) {
                        out.write(buffer, 0, tmp);//tmp is chunk size
                    }
                }
                index++;


            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return files;
    }


}
