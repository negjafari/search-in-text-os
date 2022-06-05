import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;

public class ThreadManagerWithMutex implements Runnable {

    private String[] words;
    private String outputFileName;
    private String threadFileName;

    public ThreadManagerWithMutex(String[] words, String threadFileName , String outputFileName) {
        this.words = words;
        this.threadFileName = threadFileName;
        this.outputFileName = outputFileName;
    }

    public ThreadManagerWithMutex(String[] words , String outputFileName) {
        this.words = words;
        this.outputFileName = outputFileName;
    }


    @Override
    public void run() {

        FileManager fileManager = new FileManager();

        try {
            //File file = new File(threadFileName);
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
                            LocalTime time1 = LocalTime.now();

                            //int lineInInput = fileManager.setFoundedLine(line, Thread.currentThread().getName());

                            LocalTime time2 = LocalTime.now();

                            String message = "find word " + "|" +  w + "|" + " in line "
                                    + line + " by " + Thread.currentThread().getName()
                                    + " with ID " + Thread.currentThread().getId()
                                    + " at time " + time1 + " write in output file at time " + time2 + "\n";

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