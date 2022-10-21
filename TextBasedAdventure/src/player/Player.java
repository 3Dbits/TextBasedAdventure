package player;

import battle.Battle;

@SuppressWarnings("unused")
public class Player implements PlayerInterface {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    private String name;
    private String role;
    private int hp;
    private int dmg;
    private int mana;
    private int defense;
    private int healthPot = 2;
    private int manaPot = 2;
    private int maxHP;
    private int maxMana;

    public String getIcon() {
        return "\u0D9E";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealthPot() {
        return healthPot;
    }

    public void setHealthPot(int healthPot) {
        this.healthPot = healthPot;
    }

    public int getManaPot() {
        return manaPot;
    }

    public void setManaPot(int manaPot) {
        this.manaPot = manaPot;
    }

    @Override
    public int normalAttack() {
        int multiplier = multiCheck();
        int calcDmg = getDmg() * multiplier;
        switch (multiplier) {
            case 2 -> Battle.appendTurnInfo(getName() + ANSI_RED + " CRIT " + ANSI_RESET + "attacks for " + calcDmg + " damage!\n");
            case 1 -> Battle.appendTurnInfo(getName() + " attacks for " + calcDmg + " damage!\n");
            case 0 -> Battle.appendTurnInfo(getName() + " misses!\n");
        }
        return calcDmg;

    }
    public int rogueAttack(){
        int multiplier = multiCheck();
        int calcDmg = getDmg() * multiplier;
        switch (multiplier) {
            case 2 -> Battle.appendTurnInfo(getName() + ANSI_RED + " CRIT " + ANSI_RESET + "attacks for " + calcDmg + " damage!\n");
            case 1 -> Battle.appendTurnInfo(getName() + " attacks for " + calcDmg + " damage!\n");
            case 0 -> Battle.appendTurnInfo(getName() + " misses!\n");
        }
        return calcDmg;
    }

    @Override
    public void defend() {
        setDefense(getDefense() * 2);
    }

    public void drinkHealthPot() {
        if (this.getHp() < (this.getMaxHP() * 0.5)) {
            this.setHp((int) (this.getHp() + (this.getMaxHP() * 0.5)));
        } else this.setHp(this.getMaxHP());
        setHealthPot(getHealthPot() - 1);
    }

    public void drinkManaPot() {
        if (getMana() < (getMaxMana() * 0.6)) {
            setMana((int) (getMana() + (getMaxMana() * 0.4)));
        } else setMana(getMaxMana());
        setManaPot(getManaPot() - 1);
    }

    public String getStats() {
        return (getIcon() + " " + getHp()+"/"+getMaxHP()+"HP "+getMana()+"/"+getMaxMana()+"MP "+getDmg()+"Atk/"+getDefense()+"Def");
    }

    @SuppressWarnings("ConstantConditions")
    public int multiCheck() {
        if (toString().equals("Rogue")){
            int tempRandom = (int) (Math.random() * 100);
            if (tempRandom <= 20) {
                return 2;
            } else if (tempRandom > 20 && tempRandom <= 35) {
                return 0;
            }
        }
        int tempRandom = (int) (Math.random() * 100);
        if (tempRandom <= 10) {
            return 2;
        } else if (tempRandom > 10 && tempRandom <= 35) {
            return 0;
        }
        return 1;
    }

    public int skill1() {//TODO
        reduceMana(10);
        switch (getClass().getSimpleName()) {
            case "Warrior" -> {
                rage();
                Battle.appendTurnInfo("Rage overwhelms you! " + getDmg() + " atk for rest of combat\n");
            }
            case "Rogue" -> {
                shadowWalk();
                Battle.appendTurnInfo("You hide in the shadow! Dodge 100% 3 turns\n");

            }
            case "Archer" -> {
                int totalAttack = rainOfArrows();
                Battle.appendTurnInfo("You send a volley of arrows for " + totalAttack + "dmg!\n");
                return totalAttack;
            }
            case "Wizard" -> {
                int lightningDmg = lightningStorm();
                Battle.appendTurnInfo("Ride the lightning for " + lightningDmg + "dmg!\n");
                return lightningDmg;
            }
        }
        return 0;
    }

    public int skill2() {
        reduceMana(10);
        switch (getClass().getSimpleName()) {
            case "Warrior" -> {
                int strongAttackDmg = strongAttack();
                if (strongAttackDmg > 0) {
                    Battle.appendTurnInfo("A fierce attack for " + strongAttackDmg + " damage!\n");
                } else {
                    Battle.appendTurnInfo("You attacked too relentlessly and missed!\n");
                }
                return strongAttackDmg;
            }
            case "Rogue" -> {
                int backStabDmg = backStab();
                Battle.appendTurnInfo("A cunning strike for " + backStabDmg + " damage!\n");
                return backStabDmg;
            }
            case "Archer" -> {
                int arrowDmg = arrowToTheKnee();
                Battle.appendTurnInfo("The monster used to be an adventurer like you,\nuntil you shoot it in the knee for " + arrowDmg + " damage!\n");
                return arrowDmg;
            }
            case "Wizard" -> {
                int fireballDmg = fireball();
                Battle.appendTurnInfo("Burn baby, burn for " + fireballDmg + " damage!\n");
                return fireballDmg;
            }
        }
        return 0;
    }

    public int strongAttack() {
        int tempRandom = (int) (Math.random() * 100);
        if (tempRandom <= 50) {
            return 0;
        }
        return getDmg() * 3;
    }

    public int backStab() {
        return getDmg() * 2;
    }

    public int arrowToTheKnee() {
        return getDmg() * 3;
    }

    public int fireball() {
        int tempRandom = (int) (Math.random() * 100);
        if (tempRandom >= 50) {
            return getDmg()*5;
        }
        return getDmg() * 2;
    }

    public void rage() {
        setDmg(getDmg() * 2);
    }

    public void shadowWalk() {
        setDefense(999);
        Battle.setDefTime(3);
    }

    public int rainOfArrows() {
        int totalAttack = 0;
        for (int i = 0; i < 10; i++) {
            int tempRandom = (int) (Math.random() * 100);
            if (tempRandom >= 50) {
                totalAttack += normalAttack();
            }
        }
        return totalAttack;
    }

    public int lightningStorm() {
        int totalAttack = 0;
        for (int i = 0; i < 20; i++) {
            int tempRandom = (int) (Math.random() * 100);
            if (tempRandom >= 70) {
                totalAttack += getDmg();
            }
        }
        return totalAttack;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void reduceMana(int skillCost) {
        this.setMana(this.getMana() - skillCost);
    }

    public int regenHP() {
        setHp(getHp()+2);
        return 2;
    }
    public int regenMana() {
        setMana(getMana()+5);
        return 5;
    }


    public void passive() {
        switch (getClass().getSimpleName()) {
            case "Warrior" -> {
                if (getHp() <= getMaxHP() - 2) {
                    int regenHP = regenHP();
                    Battle.appendTurnInfo(getName() + " " + regenHP + "+ HP!\n");
                }
            }
            case "Wizard" -> {
                if (getMana() <= getMaxMana() - 5) {
                    int regenMP = regenMana();
                    Battle.appendTurnInfo(getName() + " " + regenMP + "+ MP!\n");
                }
            }

        }
    }
}
