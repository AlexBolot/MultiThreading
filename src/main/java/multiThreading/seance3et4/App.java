package multiThreading.seance3et4;

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
        Thread tServeur = new Thread()
        {
            @Override
            public void run ()
            {
                Serveur.main(null);
            }
        };
        
        Thread tClient = new Thread()
        {
            @Override
            public void run ()
            {
                Client.main(null);
            }
        };
        
        tServeur.start();
        tClient.start();
        
        tServeur.join();
        tClient.join();
    }
}