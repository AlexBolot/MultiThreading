package multiThreading.seance2;

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
    private static final Object lock  = new Object();
    public static        int    found = -1;
    public int from, to;
    public byte[] table;
    public String toSearch;
    
    @Override
    public void run ()
    {
        for (int i = from; i < to; i++)
        {
            if(found != -1) return;
            
            if(foundHere(table, i, toSearch))
            {
                synchronized (lock)
                {
                    found = i;
                }
                
                return;
            }
        }
    }
    
    private boolean foundHere (byte[] table, int from, String toSearch)
    {
        for (int i = 0; i < toSearch.length(); i++)
        {
            if(table[from + i] != toSearch.charAt(i))
            {
                return false;
            }
        }
        return true;
    }
}