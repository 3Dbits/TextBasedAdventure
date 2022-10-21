/* //don't need any more, MC
package game_logic;

import battle.Battle;
import chest.Chest;
import map.SecondMap;
import monster.Boss;
import monster.Monster;
import player.Player;

public class LogicBoss {

    SecondMap secondMap = new SecondMap();
    Chest chest = new Chest();

    public void playMap(Player player, double diff) throws InterruptedException {
        Monster boss = new Boss(diff);

        do {
            secondMap.showMap();
            secondMap.move();
            if (secondMap.isNearMonster()) {
                Battle battle = new Battle();
                battle.battleStart(player,boss);
            } else if (secondMap.isNearChest()) {
                chest.randomStatUp(player);
            }
        } while(player.getHp() > 0 && boss.getHp() > 0);
    }

}
*/
