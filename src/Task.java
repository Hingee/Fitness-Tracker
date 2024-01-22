package src;

import java.util.TimerTask; 

class Task extends TimerTask   { 
    int counter; 
    public Task()   { 
        counter = 0; 
    } 
    public void run()   { 
        System.out.println(counter);
        counter++; 
    } 
    public int getCount()   { 
        return counter; 
    } 
} 
