package src;
import java.util.Timer;

public class Stopwatch {
    private boolean running; 
    private Task task; 
    private Timer timer; 
    public Stopwatch()   { 
        timer = new Timer(true); 
    } 

    public boolean isRinging()   { 
        return running; 
    } 

    public void startRinging()   { 
        running = true; 
        task = new Task(); 
        timer.scheduleAtFixedRate(task, 0, 1000); 
    } 

    public void stop()   { 
        running = false; 
        task.cancel(); 
    } 

    public int getCurrent() {
        return task.getCount();
    }
}  
