package game_interface;


import player.Player;

import java.util.Scanner;

@SuppressWarnings("unused")
public class Menu {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";

    public void welcomeText() {
        System.out.println(ANSI_GREEN + "                  [\\\n" +
                "                  |\\)                                ____\n" +
                "                  |                               __(_   )__\n" +
                "                  Y\\          ___               _(          )\n" +
                "                 T  \\       __)  )--.          (     )-----`\n" +
                "                J    \\   ,-(         )_         `---'\n" +
                "               Y/T`-._\\ (     (       _)                 __\n" +
                "               /[|   ]|  `-(__  ___)-`  |\\          ,-(  __)\n" +
                "               | |    |      (__)       J'         (     )\n" +
                "   _           | |  ] |    _           /;\\          `-  '\n" +
                "  (,,)        [| |    |    L'         /;  \\\n" +
                "             /||.| /\\ |   /\\         /.,-._\\        ___ _\n" +
                "            /_|||| || |  /  \\        | |{  |       (._.'_)\n" +
                "  L/\\       | \\| | '` |_ _ {|        | | U |   /\\\n" +
                " /v^v\\/\\   `|  Y | [  '-' '--''-''-\"-'`'   | ,`^v\\ /\\,`\\\n" +
                "/ ,'./  \\.` |[   |       [     __   L    ] |      /^v\\  \\\n" +
                ",'     `    |    |           ,`##Y.   ]    |___Y Y____,_,,_,,_\n" +
                "--   -----.-(] [ |   ]     o/####U|o      ]|| /`-, Y   _   Y  Y\n" +
                "   Y Y  --;`~T   |      }   \\####U|[\\ _,.-(^) ,-'  _  (^)__  _\n" +
                "  Y  YY   ;'~~l  |   L     [|\\###U'E'\\  \\ \\Y-` _  (^) _Y  _\n" +
                " Y  Y Y   ;\\~~/\\{| [      _,'-\\`= = '.\\_ ,`   (^)(^) (^) (^)\n" +
                "     --   ;\\~~~/\\|  _,.-'`_  `.\\_..-'\"  _ . ,_ Y_ Y_ _Y  _Y__\n" +
                "    _    _; \\~~( Y``   Y (^) / `,      (^)      _   (^) (^)\n" +
                "   (^)  (^)`._~ /  L \\  _.Y'`  _  ` --  Y - - -(^) - Y - Y -\n" +
                "    Y    Y    `'--..,-'`      (^)   _  -    _   Y ____\n" +
                "      --           _    _ --   Y   (^)   _ (^)  ===   ----\n" +
                "          __   -  (^)  (^)      --- Y   (^) Y\n" +
                "      _            Y    Y                Y             " + ANSI_RESET);
        System.out.println("\nHello adventurer, welcome to Dungeons And JAVA!");
    }

    public void mainMenu() {
        System.out.println("""
                \nChoose your error!
                \t1. Start a new project
                \t2. Exit game
                """);
    }

    public void chooseDifficulty() {
        System.out.println("""
                \nChoose the difficulty for the game:
                \t1. Java basic - monsters stats are 50% lower
                \t2. Java advanced - monsters stats are normal
                \t3. JavaFX - monsters stats are 50% higher
                \t4. Console type game - pls don't do it..
                """);

    }

    public void roleChoice() {
        System.out.println("""
                Select a starting role:
                \t1. Warrior\t ==> 100HP 10dmg 50mana 5defense
                   \t\tSkills:
                   \t\t ¤ Rage (10 mana, x2 dmg for rest of combat)
                   \t\t ¤ Strong attack (10 mana, x3 dmg 50% hit chance)
                   \t\t ¤ Passive: regen 2HP each turn
                                
                \t2. Rogue\t ==> 80HP 15dmg 60mana 3defense
                   \t\tSkills:
                   \t\t ¤ Shadow walk (10 mana, 3 turn dodge 100%)
                   \t\t ¤ Backstab (10 mana, x2 dmg attack 100% hit chance)
                   \t\t ¤ Passive: double CRIT chance and more normal hit chance
                   
                \t3. Archer\t ==> 70HP 12dmg 40mana 2defense
                   \t\tSkills:
                   \t\t ¤ Rain of arrows (10 mana, 10 attacks 50% hit chance)
                   \t\t ¤ Arrow to the knee (10 mana, 2x dmg 100% hit chance)
                   \t\t ¤ Passive: 1 free attack on combat start
                   
                \t4. Wizard\t ==> 60HP 8dmg 100mana 1defense
                   \t\tSkills:
                   \t\t ¤ Lightning storm (10 mana, 20 attacks 30% hit chance)
                   \t\t ¤ Fireball (10 mana, x2 dmg attack 50% chance of 5x dmg)
                   \t\t ¤ Passive: gain 5MP each turn
                """);

    }

    public void entrance () throws InterruptedException {
        System.out.print("You are entering the dungeon");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.println(".");
        Thread.sleep(1000);
    }
    public static void fightMenu (Player player) {
        System.out.println("\nChoose your action: " +
                "\n1. Attack " +
                "\n2. Defend " +
                "\n3. Special attack " +
                "\n4. Drink health potion +50% max HP (You have " + player.getHealthPot() + "HP potion)" +
                "\n5. Drink mana potion +40% max mana (You have " + player.getManaPot() + "MP potion)");
    }
    public static void specialMove(Player player){
        switch (player.getRole()){
            case "Warrior" -> System.out.println("""
            1. Rage (10 mana, x2 dmg for rest of combat)
            2. Strong attack (10 mana, x2 dmg 50% hit chance)
            """);
            case "Rogue" -> System.out.println("""
            1. Shadow walk (10 mana, 3 turn dodge 100%)
            2. Backstab (10 mana, x2 dmg attack 100% hit chance)
            """);
            case "Archer" -> System.out.println("""
            1. Rain of arrows (10 mana, 10 attacks 50% hit chance)
            2. Arrow to the knee (10 mana, 3x dmg 100% hit chance)
            """);
            case "Wizard" -> System.out.println("""
            1. Lightning storm (10 mana, 20 attacks 30% hit chance)
            2. Fireball (10 mana, x2 dmg attack 50% chance of 5x dmg)
            """);
        }


    }

    public void endGame (Player player) {
        if (player.getHp() <= 0) {
            System.out.println("You lose, better luck next time!");
            System.out.println(ANSI_RED +"                              /`._      ,\n" +
                    "                             /     \\   / \\\n" +
                    "                             ) ,-==-> /\\/ \\\n" +
                    "                              )__\\\\/ // \\  |\n" +
                    "                             /  /' \\//   | |\n" +
                    "                            /  (  /|/    | /\n" +
                    "                           /     //|    /,'\n" +
                    "                          // /  (( )    '\n" +
                    "                         //     // \\    |\n" +
                    "                        //     (#) |\n" +
                    "                       /        )\\/ \\   '       ____\n" +
                    "                      /        /#/   )         /,.__\\__,,--=_,\n" +
                    "                     /         \\#\\  /)      __/ + \\____,--==<\n" +
                    "                     //gnv_____/#/_/'      (\\_\\__+/_,   ---<^\n" +
                    "                                                    '==--=='" + ANSI_RESET);
        }else {
            System.out.println(ANSI_YELLOW + "                                                                     \n" +
                    "            88                                                       \n" +
                    "            \"\"              ,d                                       \n" +
                    "                            88                                       \n" +
                    "8b       d8 88  ,adPPYba, MM88MMM ,adPPYba,  8b,dPPYba, 8b       d8  \n" +
                    "`8b     d8' 88 a8\"     \"\"   88   a8\"     \"8a 88P'   \"Y8 `8b     d8'  \n" +
                    " `8b   d8'  88 8b           88   8b       d8 88          `8b   d8'   \n" +
                    "  `8b,d8'   88 \"8a,   ,aa   88,  \"8a,   ,a8\" 88           `8b,d8'    \n" +
                    "    \"8\"     88  `\"Ybbd8\"'   \"Y888 `\"YbbdP\"'  88             Y88'     \n" +
                    "                                                            d8'      \n" +
                    "                                                           d8'       " + ANSI_RESET);
        }
    }

    public String askName(String choice) {
        Scanner sc = new Scanner(System.in);

        System.out.println("An excellent choice, what is your name mighty " + choice + "?");
        String userInput = sc.nextLine();
        System.out.println("Splendid!" + " Enter the dungeon " + userInput + " and prove your worth!");
        return userInput;
    }
}
