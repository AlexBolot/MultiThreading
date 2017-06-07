package multiThreading.seance5.server;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ServerUDP	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 07/06/17 09:17
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings("InfiniteLoopStatement")
public class ServerUDP extends Server
{
    public final static  int                    port     = 8532;
    private final static int                    taille   = 1024;
    private final static byte                   buffer[] = new byte[taille];
    private static       ArrayList<InetAddress> clients  = new ArrayList<>();
    
    public static void main (String argv[])
    {
        try
        {
            DatagramSocket socket = new DatagramSocket(port);
            
            System.out.println("waiting for clients");
            while (true)
            {
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                socket.receive(data);
                if(!clients.contains(data.getAddress())) clients.add(data.getAddress());
                
                ByteArrayInputStream bis = new ByteArrayInputStream(data.getData());
                ObjectInput in = new ObjectInputStream(bis);
                
                Object o = in.readObject();
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(o);
                
                out.flush();
                sendBroadCast(bos.toByteArray());
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    private static void sendBroadCast (byte[] bytes)
    {
        for (InetAddress inetAddress : clients)
        {
            try
            {
                DatagramPacket dataSent = new DatagramPacket(bytes, bytes.length, inetAddress, port);
                DatagramSocket socket = new DatagramSocket();
                
                socket.send(dataSent);
                System.out.println("sent :" + dataSent.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println(inetAddress);
            }
        }
    }
}
