package multiThreading.seance6;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The FileScanner	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 07/06/17 11:59
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings({"InfiniteLoopStatement", "ConstantConditions"})
public class FileScanner
{
    private final static String TXT = "txt";
    private final static String PNG = "png";
    
    public static void main (String argv[])
    {
        final ArrayBlockingQueue<File> fileQueue = new ArrayBlockingQueue<>(100);
        final ArrayBlockingQueue<Long> sizeQueue = new ArrayBlockingQueue<>(100);
        
        Thread producerTxt = new Thread()
        {
            @Override
            public void run ()
            {
                File folder = new File("/Users/alexandre/Desktop/ToRead/");
                
                for (File file : folder.listFiles())
                {
                    if(file.isFile() && isFormat(file, TXT))
                    {
                        fileQueue.offer(file);
                    }
                }
            }
        };
        
        Thread producerPng = new Thread()
        {
            @Override
            public void run ()
            {
                File folder = new File("/Users/alexandre/Desktop/ToRead/");
                
                for (File file : folder.listFiles())
                {
                    if(file.isFile() && isFormat(file, PNG))
                    {
                        fileQueue.offer(file);
                    }
                }
            }
        };
        
        Thread consumerPrint = new Thread()
        {
            @Override
            public void run ()
            {
                while (true)
                {
                    try
                    {
                        File file = fileQueue.take();
                        System.out.println(file.getName());
                        sizeQueue.offer(file.length());
                    }
                    catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                }
            }
        };
        
        Thread consumerSize = new Thread()
        {
            @Override
            public void run ()
            {
                long totalSize = 0;
                
                while (true)
                {
                    try
                    {
                        long fileSize = sizeQueue.take();
                        totalSize += fileSize;
                        
                        System.out.println("Total Size = " + totalSize + " octets");
                    }
                    catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                }
            }
        };
        
        producerTxt.start();
        producerPng.start();
        consumerPrint.start();
        consumerSize.start();
    }
    
    private static boolean isFormat (File file, String format)
    {
        String name = file.getName();
        try
        {
            return name.substring(name.lastIndexOf(".") + 1).equals(format);
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
