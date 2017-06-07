package multiThreading.seance5.client;

import multiThreading.seance5.server.ServerUDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


/*................................................................................................................................
 . Copyright (c)
 .
 . The ClientUDP	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 07/06/17 09:46
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ClientUDP
{
    private final static int  taille   = 1024;
    private final static byte buffer[] = new byte[taille];
    
    public static void main (String argv[]) throws Exception
    {
        try
        {
            final DatagramSocket socket = new DatagramSocket(ServerUDP.port);
    
            Thread tWrite = new Thread()
            {
                @Override
                public void run ()
                {
                    while (true)
                    {
                        try
                        {
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            ObjectOutput out = new ObjectOutputStream(bos);
                    
                            Scanner scanner = new Scanner(System.in);
                    
                            out.writeObject(scanner.nextLine());
                            out.flush();
                            byte[] yourBytes = bos.toByteArray();
                    
                            InetAddress serveur = InetAddress.getByName("172.20.10.8");
                            int length = yourBytes.length;
                            DatagramPacket dataSent = new DatagramPacket(yourBytes, length, serveur, ServerUDP.port);
                    
                            socket.send(dataSent);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            };
    
            Thread tRead = new Thread()
            {
                @Override
                public void run ()
                {
                    while (true)
                    {
                        try
                        {
                            DatagramPacket dataRecieved = new DatagramPacket(buffer, taille);
                    
                            socket.receive(dataRecieved);
                    
                            ByteArrayInputStream bis = new ByteArrayInputStream(dataRecieved.getData());
                            ObjectInput in = new ObjectInputStream(bis);
                    
                            Object o = in.readObject();
                    
                            System.out.println("Data recieved : " + o);
                            System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
                        }
                        catch (IOException | ClassNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            };
    
            tWrite.start();
            tRead.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}