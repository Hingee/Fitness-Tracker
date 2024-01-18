package src;

import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
    private Timer timer = new Timer();  
    private TimerTask task;
    public int current;
    public Stopwatch(int time) {
        timer = new Timer();
        task = new TimerTask()  {  
            //represent the time after which the task will begin to execute  
            int i = time;  
            @Override  
            public void run()  {  
                if(i>0)  {  
                    current = i;  
                    i--;  
                }else  {  
                    System.out.println("Ring Ding Ding");  
                    //cancel the task once it is completed  
                    timer.cancel();  
                }  
            }  
        };  
    }

    public void run() {
        timer.schedule(task, 1000);  
    }

    public int getCurrent() {
        return current;
    }
}  
