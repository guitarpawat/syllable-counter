package readability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import stopwatch.*;

/**
 * Main class for testing this program.
 * @author Pawat Nakpiphatkul
 * @version 1.2
 * @since Mar 31, 2017
 */
public class Main {

    
    /**
     * Main method for running the test.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        Runnable testTask = new Task();
        TaskTimer timer = new TaskTimer();
        timer.measureAndPrint(new Task());
    }
}

/**
 * Class for does logic things for testing.
 * @author Pawat Nakpiphatkul
 */
class Task implements Runnable{
    
    private final String DICT_URL = "http://se.cpe.ku.ac.th/dictionary.txt";
    private BufferedReader reader;
    private String word;
    private int words = 0;
    private int sum = 0;
    private SyllableCounter counter = new OOSyllableCounter();
    
    /**
     * Initialize task.
     */
    public Task() {
        try {
            URL url = new URL(DICT_URL);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * @see java.lang.Runnable#run() 
     */
    public void run() {
        try {
            while((word = reader.readLine()) != null) {
                words++;
                sum += counter.countSyllables(word);
            }
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * @see java.lang.Object#toString() 
     */
    public String toString() {
        return"Words : "+words+"\nSyllables : "+sum;
    }

}
