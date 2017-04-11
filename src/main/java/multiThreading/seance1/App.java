package multiThreading.seance1;

import java.util.ArrayList;
import java.util.Random;


/*................................................................................................................................
 . Copyright (c)
 .
 . The App	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class App
{
    public static void main (String[] args) throws InterruptedException
    {
        long t1 = System.nanoTime();
        
        final Random rand = new Random();
        
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        
        for (int i = 0; i < 100; i++)
        {
            threadList.add(new MyThread());
        }
        
        for (Thread tread : threadList)
        {
            tread.start();
        }
        
        for (Thread tread : threadList)
        {
            tread.join();
        }
        
        long t2 = System.nanoTime();
        
        System.out.println(MyThread.c.get() + " - " + (t2 - t1) / 1000000);
    }
}
