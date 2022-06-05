import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.*;


public class ThreadManagerWithSemaphore extends Thread {

    Semaphore semaphore;

    private String[] words;
    private String outputFileName;
    private String threadFileName;

    public ThreadManagerWithSemaphore(String[] words, String threadFileName , String outputFileName) {
        this.words = words;
        this.threadFileName = threadFileName;
        this.outputFileName = outputFileName;
    }

    public ThreadManagerWithSemaphore(String[] words , String outputFileName, String threadFileName ,Semaphore semaphore) {
        this.words = words;
        this.outputFileName = outputFileName;
        this.threadFileName = threadFileName;
        this.semaphore = semaphore;
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

                            String message = "in " + Thread.currentThread().getName() + "-" + Thread.currentThread().getId() +
                                    " ,fileName : " + Thread.currentThread().getName() + " ,find word " + "|"
                                    + w  + "|" + " in line " + line + " at time " + java.time.LocalTime.now() + "\n";

                            semaphore.acquire();
                            fileManager.WriteResultToFileWithMutex(message);
                            semaphore.release();
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