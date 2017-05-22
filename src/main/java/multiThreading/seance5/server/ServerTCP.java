package multiThreading.seance5.server;

import multiThreading.seance3et4.operation.Operation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ServerTCP	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 22/05/17 09:21
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ServerTCP extends Server
{
    public static void main (String[] args)
    {
        ServerSocket socketserver;
        
        try
        {
            socketserver = new ServerSocket(2009);
            
            while (true)
            {
                System.out.println("Server ouvert, prêt à recevoir des Clients");
                final Socket s = socketserver.accept();
                System.out.println("Un Client s'est connecté !");
                
                //region Thread tClient = new Thread()
                Thread tClient = new Thread()
                {
                    @Override
                    public void run ()
                    {
                        try
                        {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            
                            oos.flush();
                            
                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                            
                            Operation operation = (Operation) ois.readObject();
                            
                            oos.writeObject(operation.compute());
                            System.out.println(operation.compute());
                            System.out.println();
                        }
                        catch (IOException | ClassNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                //endregion
                
                tClient.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}