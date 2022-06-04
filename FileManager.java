import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private String sourceFileName;
    private String NewFilesName = "file";
    private int filesNumber;

    private String wordsFileName;

    public FileManager(int filesNumber) {
        this.filesNumber = filesNumber;
    }

    public void getInputFiles(){

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


    }

    public ArrayList<String> fileSplitter() {

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