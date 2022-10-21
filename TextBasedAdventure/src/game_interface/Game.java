package game_interface;

import game_logic.Logic;
import map.FirstMap;
import map.Map;
import player.*;

import java.util.Scanner;

@SuppressWarnings({"unused"})
public class Game {
    private Player player;
    private final Scanner scanner = new Scanner(System.in);
    private final Menu menu = new Menu();
    private String userInput;
    private boolean isEndGame = false;
    private double diff = 0.0;
    private final Logic logic = new Logic();




    public void intro() throws InterruptedException {
        menu.welcomeText();
        boolean isOn = true;
        int exitCount = 0;

        do {
            if (isEndGame){
                System.out.println("Do you dare try again, perhaps on higher difficulty?");
            }
            menu.mainMenu();
            userInput = scanner.nextLine();
            switch (userInput){
                case "1" -> startGame();
                case "2" -> {
                    if (isEndGame){
                        isOn = false;
                    }else {

                        if (exitCount == 0) {
                            System.out.print("ERROR, you must play the game!");
                        } else if (exitCount == 1) {
                            System.out.println("Play the game!");
                        } else if (exitCount == 2) {
                            System.out.println("I won't quit!");
                        } else if (exitCount == 3) {
                            System.out.print("Okay, quitting game.");
                            Thread.sleep(1000);
                            System.out.print(".");
                            Thread.sleep(1000);
                            System.out.print(".");
                            isOn = false;
                        }
                        exitCount++;
                    }
                }
                default -> System.out.println("Enter a option from 1-2");
            }
        } while (isOn);

    }
    public void startGame() throws InterruptedException {
        Map firstMap = new FirstMap();
        menu.chooseDifficulty();
        String tempDiff;
        do {
            tempDiff = scanner.nextLine();
        } while (!(tempDiff.equals("1") || tempDiff.equals("2") || tempDiff.equals("3") || tempDiff.equals("4")));
        menu.roleChoice();
        playerRoleSet();
        menu.entrance();
        switch (tempDiff){
            case "1" -> diff = 0.5;
            case "2" -> diff = 1;
            case "3" -> diff = 1.5;
            case "4" -> diff = 2;
        }
        logic.playMap(player, diff, firstMap);
        menu.endGame(player);
        isEndGame = true;
    }

    public void playerRoleSet(){
        do {
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> {
                    player = new Warrior();
                    player.setName(menu.askName("warrior"));
                }
                case "2" -> {
                    player = new Rogue();
                    player.setName(menu.askName("rogue"));
                }
                case "3" -> {
                    player = new Archer();
                    player.setName(menu.askName("archer"));
                }
                case "4" -> {
                    player = new Wizard();
                    player.setName(menu.askName("wizard"));
                }
                default -> System.out.println("Please select a valid role from 1-4");
            }
        } while (player == null);
    }
}



