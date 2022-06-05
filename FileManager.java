import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileManager {

    private ReentrantLock mutex = new ReentrantLock();

    private String sourceFileName;
    private String NewFilesName = "thread";

    private String outputFileName = "result";
    private int filesNumber;

    private String wordsFileName;

    public FileManager(){

    }

    public FileManager(int filesNumber) {
        this.filesNumber = filesNumber;
    }

    public void getInputFiles() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file name : ");
        this.sourceFileName = sc.nextLine();


        System.out.println("Enter word's file name : ");
        this.wordsFileName = sc.nextLine();


        File inputFile = new File(this.sourceFileName);
        if (inputFile.exists()) {
            System.out.println("File size in bytes " + inputFile.length());
        }

        File wordsFile = new File(this.wordsFileName);
        if (wordsFile.exists()) {
            System.out.println("Word's file size in bytes " + wordsFile.length());
        }

        int result = createOutputFile();

    }

    public ArrayList<String> fileSplitter() {

        int index = 1;

        ArrayList<String> files = new ArrayList<>();

        File f = new File(sourceFileName);

        int size = (int) (f.length() / (filesNumber));

        byte[] buffer = new byte[size];

        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f))) {
            int tmp = 0;
            while ((tmp = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name

                if (index == filesNumber + 1) {
                    int j = index - 1;
                    try (FileOutputStream out = new FileOutputStream(NewFilesName + j, true)) {
                        out.write(buffer, 0, tmp);//tmp is chunk size
                    }
                } else {
                    String path = NewFilesName + index;
                    File newFile = new File(path);
                    files.add(path);
                    try (FileOutputStream out = new FileOutputStream(newFile)) {
                        out.write(buffer, 0, tmp);//tmp is chunk size
                    }
                }
                index++;


            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return files;
    }

    public int createOutputFile() {
        try {
            File output = new File(outputFileName + ".txt");
            if (output.createNewFile()) {
                System.out.println("Output file created ... " + output.getName());
                return 1;
            }
        } catch (IOException e) {
            System.out.println("Error creating output file ... ");
            e.printStackTrace();
            return 0;

        }
        return 0;
    }

    public String[] createWordsArray() {

        String[] words = null;

        try{
            File file = new File(this.wordsFileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;


            while((line=bufferedReader.readLine())!=null)
            {
                words=line.split(" ");
            }

            fileReader.close();
        } catch (Exception e){
            System.out.println(e);
        }



        return words;
    }

    public void writeResultToFile(String message){

        try {
            FileWriter fWriter = new FileWriter(outputFileName + ".txt", true);
            fWriter.write(message);
            fWriter.close();

        }

        catch (IOException e) {

            System.out.print(e.getMessage());
        }
    }

    public void WriteResultToFileWithMutex(String message) {
        mutex.lock();
        try {
            Files.writeString(
                    Path.of(System.getProperty("user.dir"), outputFileName + ".txt"),
                    message,
                    CREATE, APPEND);
        }catch (Exception e){
            System.out.println(e);
        } finally {
            mutex.unlock();
        }
    }
}

