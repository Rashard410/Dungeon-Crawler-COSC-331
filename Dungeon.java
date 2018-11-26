/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

// Author Rashard Brown Jr

import java.util.*;


public class Dungeon
{    Object [] dungeon;
Player player = new Player();
Monster monster = new Monster();
int PlayerLo = 0;
int MonstarLoc1;
int MonstarLoc2;
//get input from the user
Scanner kyb = new Scanner(System.in);
Object Direction;
int NumberSpaces;
public Dungeon() {
    dungeon = new Object[10];
}
public void Start() {
    boolean Win = false;
    for(int i = 0; i <= 9; i++){
        dungeon [i] = " ";
    }
    dungeon [0] = player.toString();
    //populating the dungeon with monsters
    MonstarLoc1 = NewMonster();
    dungeon [MonstarLoc1] = monster.toString();
    MonstarLoc2 = NewMonster();
    dungeon [MonstarLoc2] = monster.toString();
    //loop that stops the game when the player wins
    while(Win != true) {
        //printing the dungeon to the console
        System.out.println(Arrays.toString(dungeon));
        //for loop used to move the player around the dungeon
        while (PlayerLo < 9){
            int NumberSpaces;
            System.out.println("Enter the number of spaces you would like to move: ");
            NumberSpaces = kyb.nextInt();
            int sum = PlayerLo + NumberSpaces;
            if(sum > 9) {
                System.out.println("Please enter a number, x, such that x + PlayerLo <=9 ");
                NumberSpaces = 0;
            }
//removing the Player icon from the board
dungeon [PlayerLo] = " ";
//loops that checks each spot in the dungeon for a monster before moving the Player icon
for(int i = 0; i <= NumberSpaces; i++) {
    int PL = PlayerLo;
    if((PlayerLo+i) == MonstarLoc1) {
        String attack;
        System.out.println("You have encountered a Monster! ");
        System.out.println("Enter A to attack");
        attack = kyb.next();
        if(attack.equalsIgnoreCase("a")){
            System.out.println("You Defeated the Monster!");
            continue;
        }else{
            System.out.println("You died. Better Luck Next Time");
            System.exit(0);
        }
    }
}
PlayerLo += NumberSpaces;
//Sets Win to true if the player reaches the end of the dungeon
if(PlayerLo == 9){
    System.out.println("You win!");
    Win = true;
}
//changing the location of the player on the board
dungeon [PlayerLo] = player.toString();
System.out.println(Arrays.toString(dungeon));
        }
    }
}
public int NewMonster(){
    int r = (int) (Math.random() * (9 - 2 + 1) + 2);
    return r;
}
public boolean CheckSum(){
    if(NumberSpaces + PlayerLo > 9){
        return false;
    }
    return true;
    }
}