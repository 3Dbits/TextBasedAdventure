package player;

@SuppressWarnings("unused")
public class Rogue extends Player{
    public Rogue() {
        this.setRole("Rogue");
        this.setHp(80);
        this.setMaxHP(80);
        this.setDmg(15);
        this.setMana(60);
        this.setMaxMana(60);
        this.setDefense(3);
    }

}
