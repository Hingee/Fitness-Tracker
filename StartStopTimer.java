import java.util.Timer; 
import java.util.TimerTask; 

class Task extends TimerTask   { 
    int counter; 
    public Task()   { 
        counter = 0; 
    } 
    public void run()   { 
        counter++; 
        System.out.println("Ring " + counter); 
    } 
    public int getCount()   { 
        return counter; 
    } 
} 

public class StartStopTimer  { 
    private boolean running; 
    private Task task; 
    private Timer timer; 
    public StartStopTimer()   { 
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
    public void doIt()   { 
        running = false; 
        System.out.println(task.getCount() + " times"); 
        task.cancel(); 
    } 
    public static void main(String args[])   { 
        StartStopTimer phone = new StartStopTimer(); 
        phone.startRinging(); 
        try   { 
            System.out.println("started running..."); 
            Thread.sleep(20000); 
        }  
        catch (InterruptedException e)   { 
        } 
        phone.doIt(); 
    } 
}