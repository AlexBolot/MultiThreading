package multiThreading.seance3et4.client;

import multiThreading.seance3et4.operation.Addition;
import multiThreading.seance3et4.server.ServerUDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ClientUDP	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 24/04/17 10:45
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ClientUDP
{
    public static void main (String argv[]) throws Exception
    {
        int err = 0;
        long deb = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
        {
            
            try
            {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                
                Addition operation = new Addition();
                operation.setOperands(new double[]{1, 4, 5});
                
                out.writeObject(operation);
                out.flush();
                byte[] yourBytes = bos.toByteArray();
                
                InetAddress serveur = InetAddress.getByName("172.20.10.7");
                int length = yourBytes.length;
                DatagramPacket dataSent = new DatagramPacket(yourBytes, length, serveur, ServerUDP.port);
                DatagramSocket socket = new DatagramSocket();
                
                socket.send(dataSent);
                DatagramPacket dataRecieved = new DatagramPacket(new byte[length], length);
                socket.receive(dataRecieved);
                
                ByteArrayInputStream bis = new ByteArrayInputStream(dataRecieved.getData());
                ObjectInput in = new ObjectInputStream(bis);
                
                Object o = in.readObject();
                
                if(!((Double) o == 9)) err++;
                
                System.out.println("Data recieved : " + o);
                System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        System.out.println((deb - System.currentTimeMillis()) / 1000);
    }
}

//Dam
//172.20.10.7

//Moi
//172.20.10.8

//Chris
//172.20.10.9