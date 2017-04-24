package multiThreading.seance3et4;

import multiThreading.seance3et4.client.ClientTCP;
import multiThreading.seance3et4.server.ServerTCP;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 24/04/17 09:14
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class App
{
    public static void main (String[] args) throws InterruptedException
    {
        Thread tServeur = new Thread()
        {
            @Override
            public void run ()
            {
                ServerTCP.main(null);
            }
        };
        
        Thread tClient = new Thread()
        {
            @Override
            public void run ()
            {
                ClientTCP.main(null);
            }
        };
        
        tServeur.start();
        tClient.start();
        
        tServeur.join();
        tClient.join();
    }
}