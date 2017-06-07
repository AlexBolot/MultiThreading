package multiThreading.seance6;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The FileScanner	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 07/06/17 10:25
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class FileScanner
{
    public static void main (String argv[])
    {
        final ArrayBlockingQueue<File> blockingQueue = new ArrayBlockingQueue<>(100);
        
        Thread producer = new Thread()
        {
            @Override
            public void run ()
            {
                File folder = new File("/Users/alexandre/Desktop/ToRead/");
                
                for (File file : folder.listFiles())
                {
                    if(file.isFile()) blockingQueue.offer(file);
                }
            }
        };
        
        Thread consumer = new Thread()
        {
            @Override
            public void run ()
            {
                while (true)
                {
                    try
                    {
                        System.out.println(blockingQueue.take().getName());
                    }
                    catch (InterruptedException ignored)
                    {
                        //Nothing to do here
                    }
                }
            }
        };
        
        producer.start();
        consumer.start();
    }
}
