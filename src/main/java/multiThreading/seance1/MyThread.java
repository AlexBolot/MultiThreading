package multiThreading.seance1;

import java.util.concurrent.atomic.AtomicLong;

/*................................................................................................................................
 . Copyright (c)
 .
 . The MyThread	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class MyThread extends Thread
{
    private static final Object lock = new Object();
    
    static AtomicLong c = new AtomicLong(0);
    
    @Override
    public void run ()
    {
        for (int j = 0; j < 10000; j++)
        {
            c.incrementAndGet();
        }
        
    }
}