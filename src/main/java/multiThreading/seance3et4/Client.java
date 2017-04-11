package multiThreading.seance3et4;

import multiThreading.seance3et4.operation.Addition;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Client	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Client
{
    public static void main (String[] args)
    {
        Socket socket;
        try
        {
            //145
            socket = new Socket("192.168.43.243", 2009);
            System.out.println("Demande de connexion");
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            Addition operation = new Addition();
            operation.setOperands(new double[]{1, 3, 5});
            
            oos.writeObject(operation);
            
            System.out.println(ois.readObject());
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}