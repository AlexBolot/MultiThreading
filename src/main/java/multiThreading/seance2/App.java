package multiThreading.seance2;

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
        Random r = new Random();
        byte[] table = new byte[1000000000];
        
        for (int i = 0; i < 1000000000; i++)
        {
            table[i] = (byte) (r.nextInt(26) + 'a');
        }
        
        System.out.println(search(table, "alex".toLowerCase(), 4));
    }
    
    public static int search (byte[] table, String searchPattern, int n) throws InterruptedException
    {
        assert (n > 0);
        
        ArrayList<MyThread> threadList = new ArrayList<MyThread>();
        
        for (int i = 0; i < n; i++)
        {
            MyThread thread = new MyThread();
            thread.toSearch = searchPattern;
            thread.table = table;
            thread.from = (table.length / n) * i;
            thread.to = (table.length / n) * (i + 1);
            
            threadList.add(thread);
        }
        
        for (MyThread tread : threadList)
        {
            tread.start();
        }
        
        for (MyThread tread : threadList)
        {
            tread.join();
        }
        
        return MyThread.found;
    }
}
