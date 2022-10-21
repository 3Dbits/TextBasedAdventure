package map;

import java.util.Arrays;
import java.util.Scanner;

public class SecondMap implements Map{

    char[][] map = new char[12][15];
    private final char floor = ' ';
    char player = '\u0D9E'; //ඞ
    int[] playerPosition = new int[2];
    int[] chestPosition1 = new int[2];
    int[] chestPosition2 = new int[2];
    int[] bossPosition1 = new int[2];
    int[] bossPosition2 = new int[2];
    char chest = '\u00A9'; //©
    char wall = '\u0D04';

    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";

    public SecondMap() {
        for (var e : map) {
            for (var z : e) {
                //noinspection UnusedAssignment
                z = wall;
            }
        }
        for (int i = 1; i <= 5; i++) {
            map[5][i] = floor;
            map[6][i] = floor;
        }
        for (int i = 6; i <= 13; i++) {
            for (int j = 2; j <= 9; j++){
                map[j][i] = floor;
            }
        }
        for (int i = 7; i <= 12; i++) {
            map[1][i] = floor;
            map[10][i] = floor;
        }
        setPlayerOnMap();
        setChestOnMap();
        setMonsterOnMap();
    }

    @Override
    public void setPlayerOnMap() {
        map[6][1] = player;
        playerPosition[0] = 6;
        playerPosition[1] = 1;
    }

    @Override
    public void setMonsterOnMap() {
        map[4][9] = '(';
        map[4][10] = '_';
        map[4][11] = ')';
        map[5][9] = ':';
        map[5][10] = '=';
        map[5][11] = ':';
        map[6][9] = ':';
        map[6][10] = '=';
        map[6][11] = ':';
        map[7][9] = '(';
        map[7][10] = '~';
        map[7][11] = ')';
        bossPosition1[0] = 5;
        bossPosition1[1] = 10;
        bossPosition2[0] = 6;
        bossPosition2[1] = 10;


    }

    @Override
    public void setChestOnMap() {
        map[4][3] = chest;
        chestPosition1[0] = 4;
        chestPosition1[1] = 3;
        map[7][3] = chest;
        chestPosition2[0] = 7;
        chestPosition2[1] = 3;

        if (Arrays.equals(chestPosition1, playerPosition)){
            map[chestPosition1[0]][chestPosition1[1]] = player;
        } else if (Arrays.equals(chestPosition2, playerPosition)){
            map[chestPosition2[0]][chestPosition2[1]] = player;
        }
    }

    @Override
    public void move() {
        Scanner sc = new Scanner(System.in);
        @SuppressWarnings("UnusedAssignment") String input = "A";

        input = sc.nextLine();

        switch (input) {
            case "w" :
                if (!checkWall(new int[]{playerPosition[0] - 1, playerPosition[1]})) {
                    map[playerPosition[0]][playerPosition[1]] = floor;
                    playerPosition[0] -= 1;
                    map[playerPosition[0]][playerPosition[1]] = player;
                }
                break;
            case "s" :
                if (!checkWall(new int[]{playerPosition[0] + 1, playerPosition[1]})) {
                    map[playerPosition[0]][playerPosition[1]] = floor;
                    playerPosition[0] += 1;
                    map[playerPosition[0]][playerPosition[1]] = player;
                }
                break;
            case "d" :
                if (!checkWall(new int[]{playerPosition[0], playerPosition[1] + 1})) {
                    map[playerPosition[0]][playerPosition[1]] = floor;
                    playerPosition[1] += 1;
                    map[playerPosition[0]][playerPosition[1]] = player;
                }
                break;
            case "a" :
                if (!checkWall(new int[]{playerPosition[0], playerPosition[1] - 1})) {
                    map[playerPosition[0]][playerPosition[1]] = floor;
                    playerPosition[1] -= 1;
                    map[playerPosition[0]][playerPosition[1]] = player;
                }
                break;
            default :
                break;
        }
        showMap();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean checkWall(int[] position) {
        return map[position[0]][position[1]] != floor &&
                map[position[0]][position[1]] != '(' &&
                map[position[0]][position[1]] != ')' &&
                map[position[0]][position[1]] != '_' &&
                map[position[0]][position[1]] != '=' &&
                map[position[0]][position[1]] != ':' &&
                map[position[0]][position[1]] != '~' &&
                map[position[0]][position[1]] != chest;
    }

    public boolean isNearMonster() {
        return Arrays.equals(playerPosition, bossPosition1) ||
                Arrays.equals(playerPosition, bossPosition2);
    }

    public boolean isNearChest() {
        return Arrays.equals(playerPosition, chestPosition1) ||
                Arrays.equals(playerPosition, chestPosition2);
    }

    @Override
    public boolean isNearExit() { //final map dont have exit
        return false;
    }

    @Override
    public void setMapCordToFloor() {
        if (Arrays.equals(playerPosition, chestPosition1)) {
            chestPosition1[0] = 0;
            chestPosition1[1] = 0;
        } else if ( Arrays.equals(playerPosition, chestPosition2)) {
            chestPosition2[0] = 0;
            chestPosition2[1] = 0;
        }
    }

    public void showMap() {
        for (var e : map) {
            System.out.println();
            for (var z : e) {
                if (z == '=') {
                    System.out.print(ANSI_RED + z + "  " + ANSI_RESET);
                } else if (z == chest || z == '(' || z == ')' || z == '~' || z == '_'  || z == ':') {
                    System.out.print(ANSI_YELLOW + z + "  " + ANSI_RESET);
                } else if (z == player) {
                    System.out.print(ANSI_BLUE + z + "  " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_GREEN + z + "  " + ANSI_RESET);
                }
            }
        }
        setMonsterOnMap();
        seePlayer();
    }

    public void seePlayer() {
        map[playerPosition[0]][playerPosition[1]] = player;
    }
}
