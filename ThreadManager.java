import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ThreadManager implements Runnable {

    private String[] words;
    private String outputFileName;
    private String threadFileName;

    public ThreadManager(String[] words, String threadFileName , String outputFileName) {
        this.words = words;
        this.threadFileName = threadFileName;
        this.outputFileName = outputFileName;
    }

    public ThreadManager(String[] words , String outputFileName) {
        this.words = words;
        this.outputFileName = outputFileName;
    }


    @Override
    public void run() {

        FileManager fileManager = new FileManager();

        try {
            File file = new File(Thread.currentThread().getName());
            String[] fileWords = null;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;

            int line = 0;

            while((s=bufferedReader.readLine())!=null)
            {

                line++;
                String replace = s.replaceAll("\\W" , " ");
                fileWords=replace.split(" ");
                for (String word : fileWords)
                {
                   for (String w : this.words) {
                       if (w.equals(word)){

                           String message = "in " + Thread.currentThread().getName() + "-" + Thread.currentThread().getId() +
                                   " ,fileName : " + Thread.currentThread().getName() + " ,find word " + "|"
                                   + w  + "|" + " in line " + line + " at time " + java.time.LocalTime.now() + "\n";
                           //System.out.print(message);
                           //fileManager.writeResultToFile(message);
                           fileManager.WriteResultToFileWithMutex(message);
                       }
                   }

                }
            }

            fileReader.close();
        }catch (Exception e){
            System.out.println(e);
        }



    }
}