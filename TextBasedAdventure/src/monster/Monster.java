package monster;

import battle.Battle;

@SuppressWarnings("unused")
public class Monster implements Monsters{
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";

    private int hp;
    private int dmg;
    private int mana;
    private int defense;
    private int maxHP;
    private int maxMana;

    public char getIcon() {
        char monsterIcon = ' ';
        switch (toString()){
            case "InfiniteLoop" -> monsterIcon = '\u267E'; //♾
            case "JavaFX" -> monsterIcon = '\u2620'; //☠
            case "MergeConflict" -> monsterIcon = '\u2694'; //⚔
            case "MissingCurlyBracket" -> monsterIcon = '\u007D'; // }
            case "NullPointerException" -> monsterIcon = '\u26A0'; // ⚠
        }
        return monsterIcon;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public int attack() {
        int multiplier = multiCheck();
        int calcDmg = getDmg()*multiplier;
        switch (multiplier){
            case 2 -> Battle.appendTurnInfo(this + ANSI_RED + " CRIT " + ANSI_RESET + "attacks for " + calcDmg + " damage!\n");
            case 1 -> Battle.appendTurnInfo(this + " attacks for " + calcDmg + " damage!\n");
            case 0 -> Battle.appendTurnInfo(this + " misses!\n");
        }
        return calcDmg;

    }
    public String getStats(){
        return (getIcon() + " " + getHp()+"/"+getMaxHP()+"HP "+getMana()+"/"+getMaxMana()+"MP "+getDmg()+"Atk/"+getDefense()+"Def");
    }

    @SuppressWarnings("ConstantConditions")
    public int multiCheck() {
        int tempRandom = (int)(Math.random() * 100);
        if (tempRandom <= 10) {
            return 2;
        } else if (tempRandom > 10 && tempRandom <= 35) {
            return 0;
        }
        return 1;
    }
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
