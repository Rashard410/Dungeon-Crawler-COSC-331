/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

//AuthorRashard Brown Jr

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static int Port = 8950;
    public static DatagramSocket socket;
    
    public static void main(String [] args) throws SocketException, UnknownHostException, IOException {
        socket = new DatagramSocket();
        while(true) {
            if(args.length != 1){
                System.out.println("Usage: CrawlerClient <hostName>");
                return;
            }
            System.out.println("Searching for a server\n");
            byte[] buf = new byte[1024];
            InetAddress add = InetAddress.getByName(args[0]);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, add, 8950);
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            System.out.println("Packet Recieved ");
            String in = new String(packet.getData(), 0, packet.getLength());
            System.out.println(in.toString());
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            System.out.println();
            if(packet.equals("move")){
                Object m = getMove();
                buf.equals(m);
                packet = new DatagramPacket(buf, buf.length);
                socket.send(packet);
            }
        }
    }
    public static Object getMove() {
        Object move;
        Scanner kyb = new Scanner(System.in);
        System.out.println("Enter: ");
        move = kyb.next();
        return move;
    }
}