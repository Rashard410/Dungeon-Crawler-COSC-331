/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

//Author Rashard Brown Jr

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;



public class Crawler {

    //game Objects
    public static Object [] dungeon = new Object[10];
    public static Player player = new Player();
    public static Monster monster = new Monster();
    public static int PlayerLo = 0;
    public static int Monstar1;
    public static int Monstar2;
    public static DatagramPacket packet;
    public static InetAddress address;
    public static byte[] a = new byte[1024];
    public static DatagramSocket socket;
    public static boolean Win = false;

    //Get input from the user
    public static Scanner kyb = new Scanner(System.in);
    public static Object Direction;
    public static int NumberSpaces;
    
    public static void main(String [] args) throws Exception {
        socket = new DatagramSocket(8950);
        System.out.println("Crawler server is currently running...\n");
        try {
//recieve request 
packet = new DatagramPacket(a, a.length);
socket.receive(packet);
System.out.println("User is Connected....\n");            
//clearig the dungeon
for(int j = 0; j <= 9; j++){
    dungeon [j] = " ";
}
//the dungeon
dungeon [0] = player.toString();
//putting monster in
Monstar1 = NewMonster();
dungeon [Monstar1] = monster.toString();
Monstar2 = NewMonster();
dungeon [Monstar2] = monster.toString();

String game = Arrays.toString(dungeon);
a = game.getBytes();
address = packet.getAddress();
int Port = packet.getPort();
packet = new DatagramPacket(a, a.length, address, Port);
socket.send(packet);
//stops game
while(Win != true) {
//move the player
while (PlayerLo < 9) {
    System.out.println("The game has started....");
    int NumberSpaces;
    String move = "Enter the amout of spaces you would like to move: ";
    SendPacket(move);
    int Packet = (int) GetPacket();
    
    NumberSpaces = Packet;
    int sum = PlayerLo + NumberSpaces;
    if(sum > 9) {
        System.out.println("Please enter a number x such that x + PlayerLo <=9 ");
        NumberSpaces = 0;
    }
    dungeon [PlayerLo] = " ";
    for(int j = 0; j <= NumberSpaces; j++) {
        int Player = PlayerLo;
        if((PlayerLo+j) == Monstar1) {
            String attack;
            System.out.println("You have ran into a Monster! ");
            System.out.println("Enter A to attack");
            attack = kyb.next();
            if(attack.equalsIgnoreCase("a")){
                System.out.println("You have defeated the Monster!");
                continue;
            } else {
                System.out.println("You died. Best Of Luck Next Time! =(");
                System.exit(0);
            }
        }
    }
    PlayerLo += NumberSpaces;
    if(PlayerLo == 9){
        System.out.println("Congratulations you win! =) ");
        Win = true;
    }
    dungeon [PlayerLo] = player.toString();
    System.out.println(Arrays.toString(dungeon));
    }
}
        } finally {
            socket.close();
        }
    }
    public static int NewMonster(){
        int CC = (int) (Math.random() * (9 - 2 + 1) + 2);
        return CC;
    }
    public static boolean CheckSum(){
        if(NumberSpaces + PlayerLo > 9){
            return false;
        }
        return true;
    }
    public static void SendPacket(String data) throws IOException{
        packet = new DatagramPacket(a, a.length);
        socket.receive(packet);
        System.out.println("Client Request....\n");
        address = packet.getAddress();
        int Port = packet.getPort();
        a = data.getBytes();
        packet = new DatagramPacket(a, a.length, address, Port);
        socket.send(packet);
        System.out.println("Packet has been sent...\n" + packet.toString());
    }
    public static Object GetPacket() throws IOException{
        Object input = "move";
        a.equals(input);
        packet = new DatagramPacket(a, a.length);
        socket.send(packet);
        packet = new DatagramPacket(a, a.length);
        socket.receive(packet);
        System.out.println("Packet has been recived...\n");
        return (Object) packet;
    }
}