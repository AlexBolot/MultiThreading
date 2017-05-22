package multiThreading.seance5.server;

import multiThreading.seance3et4.operation.Operation;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ServerUDP	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 22/05/17 09:47
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings("InfiniteLoopStatement")
public class ServerUDP extends Server
{
    public final static  int                      port     = 8532;
    private final static int                      taille   = 1024;
    private final static byte                     buffer[] = new byte[taille];
    private static       ArrayList<SocketAddress> clients  = new ArrayList<>();
    
    public static void main (String argv[])
    {
        try
        {
            DatagramSocket socket = new DatagramSocket(port);
            clients.add(socket.getRemoteSocketAddress());
            
            System.out.println("waiting for clients");
            while (true)
            {
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                socket.receive(data);
                
                ByteArrayInputStream bis = new ByteArrayInputStream(data.getData());
                ObjectInput in = new ObjectInputStream(bis);
                
                Object o = in.readObject();
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                
                out.writeObject(((Operation) o).compute());
                out.flush();
                data.setData(bos.toByteArray());
                
                System.out.println(o);
                System.out.println(data.getAddress());
                socket.send(data);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
