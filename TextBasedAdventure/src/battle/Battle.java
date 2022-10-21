package battle;

import chest.Chest;
import game_interface.Menu;
import monster.*;
import monster.NullPointerException;
import player.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings({"unused", "UnnecessaryToStringCall"})
public class Battle {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";

    Player player;
    Monster monster;
    Scanner scanner = new Scanner(System.in);
    String userInput;
    static StringBuilder turnInfo = new StringBuilder();
    static int defTime;

    public static int getDefTime() {
        return defTime;
    }

    public static void appendTurnInfo(String string) {
        Battle.turnInfo.append(string);
    }

    public static void setDefTime(int defTime) {
        Battle.defTime = defTime;
    }

    public Monster randMonster(double diff){
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new NullPointerException(diff));
        monsters.add(new InfiniteLoop(diff));
        monsters.add(new MissingCurlyBracket(diff));
        monsters.add(new MergeConflict(diff));
        int random = new Random().nextInt(monsters.size());
        return monsters.get(random);
    }

    public void battleStart(Player player,Monster monster) throws InterruptedException {

        boolean hasWon;
        setDefTime(0);

        int startDef = player.getDefense();
        int startDmg = player.getDmg();
        int turn = 0;

        turnInfo.append(ANSI_RED + "\t\t\t  !!BATTLE START!!\n" + ANSI_RESET);

        do {
            if (turn == 3){
                monster.setDmg(monster.getDmg()*2);
                turnInfo.append("Monster gets angry and doubles it's damage!");
            }
            if (player.toString().equals("Archer")){
                monster.setHp(monster.getHp() - (player.getDmg() - monster.getDefense()));
                //noinspection UnnecessaryToStringCall
                turnInfo.append(monster.toString()).append(" took ").append(player.getDmg() - monster.getDefense()).append(" damage.\n");
            }
            battleScreen(player, monster);
            turnInfo.delete(0, turnInfo.length());

            boolean isValidChoice = false;

            if (player.getHp() <= 0) {
                break;
            }

            do {
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> {
                    isValidChoice = true;
                    int playerAttack;

                    if (player.toString().equals("Rogue")){
                        playerAttack = player.rogueAttack();//rogue attack
                    }else {
                    playerAttack = player.normalAttack();//normal attack
                    }
                    if (monster.getDefense() >= playerAttack) {
                        turnInfo.append("You don't do any damage to the monster!\n");
                    } else {
                        monster.setHp(monster.getHp() - (playerAttack - monster.getDefense()));
                        //noinspection UnnecessaryToStringCall
                        turnInfo.append(monster.toString()).append(" took ").append(playerAttack - monster.getDefense()).append(" damage.\n");
                    }
                }
                case "2" -> {
                    isValidChoice = true;
                    player.defend();
                    turnInfo.append("You prepare to defend.\n");
                    battleScreen(player, monster);
                    //noinspection BusyWait
                    Thread.sleep(2000);
                    turnInfo.delete(0, turnInfo.length());
                    defTime = 3;
                }
                case "3" -> {
                    if (player.getMana() >= 10) {
                        isValidChoice = true;
                        Menu.specialMove(player);
                        userInput = scanner.nextLine();
                        if (userInput.equals("1")) {//TODO
                            if (player.toString().equals("Archer") || player.toString().equals("Wizard")) {
                                int skillDmg = player.skill1();
                                int monsterReceivedDmg = skillDmg - monster.getDefense();
                                monster.setHp(monster.getHp() - (monsterReceivedDmg));
                                turnInfo.append(monster.toString()).append(" took ").append(monsterReceivedDmg).append(" damage.\n");

                            } else {
                                player.skill1();
                            }
                        } else if (userInput.equals("2")) {
                            int skillDmg = player.skill2();
                            int monsterReceivedDmg = skillDmg - monster.getDefense();
                            monster.setHp(monster.getHp() - (monsterReceivedDmg));
                            turnInfo.append(monster.toString()).append(" took ").append(monsterReceivedDmg).append(" damage.\n");
                        }
                    } else {
                        turnInfo.append("You have insufficient mana!");
                        battleScreen(player, monster);
                        turnInfo.delete(0, turnInfo.length());
                    }
                }
                case "4" -> {
                    if (player.getHealthPot() > 0) {
                        player.drinkHealthPot();
                        turnInfo.append("You drink a health potion and feel much better!\n");
                    } else {
                        turnInfo.append("You're out of health potions!\n");
                    }
                    battleScreen(player, monster);
                    turnInfo.delete(0, turnInfo.length());
                }
                case "5" -> {
                    if (player.getManaPot() > 0) {
                        player.drinkManaPot();
                        turnInfo.append("You drink a mana potion and feel more focused!\n");
                    } else {
                        turnInfo.append("You're out of mana potions!\n");
                    }
                    battleScreen(player, monster);
                    turnInfo.delete(0, turnInfo.length());
                }
            }

            }while (!isValidChoice);
            if (monster.getHp() <= 0) {
                //noinspection UnnecessaryToStringCall
                System.out.printf("You've defeated the %s!! Here is your reward:",monster.toString());
                player.setDefense(startDef);
                player.setDmg(startDmg);

                Chest chest = new Chest();
                chest.randomStatUp(player);
                boolean enterDetect;
                do {
                    System.out.print("\nPress enter to continue...");
                    userInput = scanner.nextLine();
                    enterDetect = !userInput.equals("");
                }while (enterDetect);
                break;
            }

            int monsterAttack = monster.attack();
            if (player.getDefense()>=monsterAttack){
                turnInfo.append("Your take no damage!\n");
            } else {
            player.setHp(player.getHp() - (monsterAttack - player.getDefense()));//monster attacks last to make it a bit easier
            turnInfo.append(player.getName()).append(" took ").append(monsterAttack - player.getDefense()).append(" damage.\n");
            }
            if (player.getDefense()>startDef){

            if (defTime>0){
            defTime--;
            }else if (defTime == 0) {
                player.setDefense(startDef);
                turnInfo.append("Defense reset to ").append(startDef).append("\n");
            }
            }
            //passive at the end of turn
            player.passive();
            turn++;
        } while (true);
        turnInfo.delete(0, turnInfo.length());


    }



    public void battleScreen(Player player,Monster monster){
        System.out.println();
        System.out.printf("""
                #----#----#----#----#----#----#----#----#----#
                \t\t\t\t\t\t%s
                \t\t\t\t\t\t%s
                
                
                %s
                
                
                  %s
                  %s
                #----#----#----#----#----#----#----#----#----#
                """,
                //monster info
                monster.getClass().getSimpleName(),
                monster.getStats(),

                //battle-messages
                turnInfo,

                //player info
                (player.getRole() + " " + player.getName()),
                player.getStats());
        Menu.fightMenu(player);

    }


}
